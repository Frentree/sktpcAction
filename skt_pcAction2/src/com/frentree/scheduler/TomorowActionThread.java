package com.frentree.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.util.config.AppConfig;
import com.app.util.config.IoptsCurl;
import com.frentree.recon.vo.groupall.scheduleCo;
import com.google.gson.Gson;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.recon.util.database.ibatis.SqlMapInstance;
import com.recon.util.database.ibatis.tr.DBInsertTable;
import com.recon.util.database.ibatis.vo.netSchduleStatusVo;
import com.recon.util.database.ibatis.vo.netScheduleVo;
import com.recon.util.database.ibatis.vo.remediateActionVo;
import com.recon.util.database.ibatis.vo.schedulePathActionVo;
import com.recon.util.database.ibatis.vo.scheduleTargetsVo;
import com.recon.util.database.ibatis.vo.scheduleTomorowActionVo;

public class TomorowActionThread implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(TomorowActionThread.class);
	private DBInsertTable tr = null;
	private static SqlMapClient sqlMap = null;			
	
	
	public TomorowActionThread() {
		this.sqlMap = SqlMapInstance.getSqlMapInstance();	// agent select 위해 추가 shlee // 20200828
		this.tr = new DBInsertTable();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				executeAction();
				
				Thread.sleep(3000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void executeAction() {
		List<scheduleTargetsVo> sList = null;
		List<schedulePathActionVo> list = null;
		
		int success = 0;
		try {
			sList = this.sqlMap.queryForList("query.getScheduleCompletTarget");
			list = this.sqlMap.queryForList("query.getTomorrowPathID");
			
			if(list.size() > 0) {
				logger.info("TomorowActionThread DATA Count >>> : " + list.size() + " ");
			}
			
			for (schedulePathActionVo vo : list) {
				logger.info("TomorowActionThread" + vo.toString());
				
				if(vo.getAction() == 3){// 익일 삭제
					JSONArray pathIdArr = new JSONArray();
					vo.setSuccess(1);
					
					// 즉시 삭제
					if(vo.getFid() != null && !vo.getFid().equals("")) {
						pathIdArr.put(vo.getFid());
						success = executeDeleteRun(vo, pathIdArr);
					} else { // DB 찾은후 삭제
						List<String> fList = this.sqlMap.queryForList("query.getFindPathID", vo);
						
						for (String fvo : fList) {
							pathIdArr.put(fvo);
						}
						
						success = executeDeleteRun(vo, pathIdArr);
					}
					
					if(success == 1) {
						this.sqlMap.insert("update.actionJob", vo);
					}
					
				} else if(vo.getAction() == 4){// 익일 암호화
					this.sqlMap.insert("insert.drmJob", vo);
					this.sqlMap.insert("update.actionJob", vo);
				}
				
			}
			
			for (scheduleTargetsVo sVo : sList) {
				if(sVo.getAction() == 4) {
					//익일 암호화 정책일시 DRM 리스트 추출
					List<remediateActionVo> rList = this.sqlMap.queryForList("query.getDRMList");
					
					for (remediateActionVo rvo : rList) {
						//경로명에 확장자 앞에  _decrypted 추가 
						updatePathDecrypted(rvo);
						rvo.setAction(4);
						logger.info("remediate Insert >> " + rvo);
						
						//pi_remediate 테이블 삽입
						this.sqlMap.insert("insert.remediateJob", rvo);
						
						rvo.setPath(rvo.getPath().replaceAll("\\\\\\\\", "\\\\"));
						rvo.setPath_orig(rvo.getPath_orig().replaceAll("\\\\\\\\", "\\\\"));
						
						//해당 경로 pi_find에서 deldate null
						this.sqlMap.insert("update.remediateFindJob", rvo);
						//pi_remediate 한번 쌓이면 flag값을 줘서 다시 안쌓이도록 업데이트 진행
						this.sqlMap.insert("update.remediation_chkJob", rvo);
					}
				}
			}
			
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	
	// 삭제 API
	public int executeDeleteRun(schedulePathActionVo paVo, JSONArray pathIdArr) {
		int ap_no = paVo.getAp_no();
		int result = 0;
		
		String user = (ap_no == 0) ? AppConfig.getProperty("config.recon.user") : AppConfig.getProperty("config.recon.user_"+(ap_no+1));
		String pass = (ap_no == 0) ? AppConfig.getProperty("config.recon.pawwsord") : AppConfig.getProperty("config.recon.pawwsord_"+(ap_no+1));
		String ip = (ap_no == 0) ? AppConfig.getProperty("config.recon.ip") : AppConfig.getProperty("config.recon.ip_"+(ap_no+1)) ;
		String port = AppConfig.getProperty("config.recon.port");
		String api_ver = AppConfig.getProperty("config.recon.api.version");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		
		String curlurl = String.format("-k -X POST -u %s:%s 'https://%s:%s/%s/targets/%s/locations/%s/remediation/delete'",
				user, pass, ip, port, api_ver, paVo.getTarget_id(), "8987302884414283716");
		
		JSONObject obj = new JSONObject();
		obj.put("path", paVo.getPath());
		obj.put("sign_off", "picenter");
		obj.put("reason", "Remediation Delete");
		obj.put("object_ids", pathIdArr);

		String[] array = curlurl.split(" ");

		String json_string;
		json_string = new IoptsCurl().opt(array).header("Content-Type: application/json").data(obj.toString()).exec(null);

		logger.info(json_string);
		
		if (json_string == null || json_string.length() < 1 || json_string.contains("Resource not found.")) {
			logger.error("Data Null Check IP or ID: " + curlurl);
		} else {
			result = 1;
		}
		
		return result;
	}
	
	private netSchduleStatusVo executeRun(netScheduleVo vo) {
		String user = (vo.getAp_no() == 0) ? AppConfig.getProperty("config.recon.user") : AppConfig.getProperty("config.recon.user_"+(vo.getAp_no()+1));
		String pass = (vo.getAp_no() == 0) ? AppConfig.getProperty("config.recon.pawwsord") : AppConfig.getProperty("config.recon.pawwsord_"+(vo.getAp_no()+1));
		String ip = (vo.getAp_no() == 0) ? AppConfig.getProperty("config.recon.ip") : AppConfig.getProperty("config.recon.ip_"+(vo.getAp_no()+1)) ;
		String port = AppConfig.getProperty("config.recon.port");
		String api_ver = AppConfig.getProperty("config.recon.api.version");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:m:s");
		
		Calendar cal = Calendar.getInstance();
		
		String nowDate = vo.getSchedule_time();
		Gson gson = new Gson();
		
		JSONObject obj = new JSONObject();
		obj.put("label", vo.getName() + " " + sdf.format(new Date()));
		obj.put("start", nowDate);
		
		JSONArray targetArr = new JSONArray();
		JSONObject targetObj = new JSONObject();
		JSONArray locationArr = new JSONArray();
		JSONObject locationObj = new JSONObject();
		
		locationObj.put("id", vo.getLocation_id());
		locationArr.put(locationObj);
		
		targetObj.put("id", vo.getTarget_id());
		targetObj.put("locations", locationArr);
		targetArr.put(targetObj);
		obj.put("targets", targetArr);
		
		JSONArray dataTypeArr = new JSONArray();
		dataTypeArr.put(vo.getDatatype_id());
		obj.put("profiles", dataTypeArr);
		
		obj.put("repeat_days", 0);
		obj.put("repeat_months", 0);
		obj.put("cpu", "low");
		obj.put("throughput", 0);
		obj.put("memory", 0);
		obj.put("pause", new JSONObject());
		obj.put("trace", true);
		obj.put("timezone", "Default");
		obj.put("capture", false);
		logger.info(obj.toString());
		
		String curlurl = String.format("-k -X POST -u %s:%s 'https://%s:%s/%s/schedules'",
				user, pass, ip, port, api_ver);
		
		logger.info("SchedulerCheck curlurl [" + curlurl + "]");
		String[] array = curlurl.split(" ");
		String json_string = new IoptsCurl().opt(array).header("Content-Type: application/json").data(obj.toString()).exec(null);
		
		logger.info(json_string);

		scheduleCo g = gson.fromJson(json_string, scheduleCo.class);
		
		netSchduleStatusVo sVo = new netSchduleStatusVo(g.getId(), vo.getAp_no());
		
		return sVo;
	}
	
	private String updatePathDecrypted(remediateActionVo rvo) {
		String pathOrig = rvo.getPath();
		String decryptedPath = "";
		int index = pathOrig.lastIndexOf(".");
		String extension = pathOrig.substring(index + 1);
		
		if(index > 0) {
			decryptedPath = pathOrig.substring(0, index) + "_decrypted." + extension;
		}else {
			decryptedPath = pathOrig;
		}
		
		rvo.setPath_orig(pathOrig);
		rvo.setPath(decryptedPath);
		
		return decryptedPath;
		
	}
	
	

}
