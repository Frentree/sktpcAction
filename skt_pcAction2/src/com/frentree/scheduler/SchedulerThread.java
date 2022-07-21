package com.frentree.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.recon.util.database.ibatis.vo.netScheduleIdVo;
import com.recon.util.database.ibatis.vo.netScheduleVo;
import com.recon.util.database.ibatis.vo.schedulePathActionVo;
import com.recon.util.database.ibatis.vo.scheduleTargetsVo;

public class SchedulerThread implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(SchedulerThread.class);
	private DBInsertTable tr = null;
	private static SqlMapClient sqlMap = null;			
	
	
	public SchedulerThread() {
		this.sqlMap = SqlMapInstance.getSqlMapInstance();	// agent select 위해 추가 shlee // 20200828
		this.tr = new DBInsertTable();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				getSchedules();
				
				Thread.sleep(15000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getSchedules() {
		List<scheduleTargetsVo> list = null;
		
		List<netSchduleStatusVo> nList = new ArrayList<>();
		
		try {
			list = this.sqlMap.queryForList("query.getScheduleCompletTarget");
			
			/*
			 * 1. 망/그룹/PC 검색 완료한 검출 스케쥴 검색
			 * 2. 검색 진행중인 항목 next_scan이 현재 시간이 지낫는데 현재 상태가 scheduled 인 항목 stop 처리
			 * 3. 정책별 익일 암호화, 익일 삭제, 즉시 삭제, 즉시 암호화
			 * 
			 */
			for (scheduleTargetsVo vo : list) {
				logger.info("Server Name : " + vo.getName() + ", Schedule Action " );
				executeAction(vo);
			}
			
			/*for (netSchduleStatusVo vo : nList) {
				logger.info("ap_no : " + vo.getAp_no() + ", schedule_id : " + vo.getSchedule_id());
				executeScheduleRun(vo.getAp_no(), Integer.parseInt(vo.getSchedule_id()), 1);
				
				Thread.sleep(500);
			}*/
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	private void executeAction(scheduleTargetsVo vo) {
		List<schedulePathActionVo> paList = null;
		int succes = 0;
		
		try {
			String file = AppConfig.getProperty("config.file.extension");
			String[] fileArray = file.split(",");
			vo.setFileArray(fileArray);
			paList = this.sqlMap.queryForList("query.getFindPath", vo);
			logger.info("Size : " + paList.size());
			if(paList.size() > 0 ) {
				logger.info(vo.getName() + " Server Count : " + paList.size());
			}
			
			for (schedulePathActionVo paVo : paList) {
				paVo.setName(vo.getName());
				paVo.setAction(vo.getAction());
				
				if((vo.getRrn_cnt() != 0 && paVo.getType1() >= vo.getRrn_cnt()) || (vo.getForeigner_cnt() != 0 && paVo.getType2() >= vo.getForeigner_cnt()) ||
					(vo.getPassport_cnt() != 0 && paVo.getType3() >= vo.getPassport_cnt()) || (vo.getDriver_cnt() != 0 && vo.getDriver_cnt() >= paVo.getType3()) ||
					(vo.getAccount_cnt() != 0 && paVo.getType5() >= vo.getAccount_cnt()) || (vo.getCard_cnt() != 0 && paVo.getType6() >= vo.getCard_cnt()) ||
					(vo.getEmail_cnt() != 0 && paVo.getType7() >= vo.getEmail_cnt()) || (vo.getMobile_phone_cnt() != 0 && paVo.getType8() >= vo.getMobile_phone_cnt())) {
					
					if(vo.getAction() == 1) { // 즉시 삭제
						JSONArray pathIdArr = new JSONArray();
						paVo.setSuccess(1);
						
						// 즉시 삭제
						if(paVo.getFid() != null && !paVo.getFid().equals("")) {
							pathIdArr.put(paVo.getFid());
							succes = executeDeleteRun(paVo, pathIdArr);
						} else { // DB 찾은후 삭제
							List<String> fList = this.sqlMap.queryForList("query.getFindPathID", paVo);
							
							for (String fvo : fList) {
								pathIdArr.put(fvo);
							}
							
							succes = executeDeleteRun(paVo, pathIdArr);
						}
						
						if(succes == 1) {
							this.sqlMap.insert("insert.actionJob", paVo);
						}
						
					} else if(vo.getAction() == 2){ // 즉시 암호화
						paVo.setSuccess(1);
						this.sqlMap.insert("insert.actionJob", paVo);
						this.sqlMap.insert("insert.drmJob", paVo);
						
					} else if(vo.getAction() == 3){// 익일 삭제
						paVo.setSuccess(0);
						this.sqlMap.insert("insert.actionJob", paVo);
						
					} else if(vo.getAction() == 4){// 익일 암호화
						paVo.setSuccess(0);
						this.sqlMap.insert("insert.actionJob", paVo);
						
					}
				}
				
			}
			
		}catch (Exception e) {
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
		obj.put("throughput", 5);
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
	
	

}
