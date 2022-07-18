package com.frentree.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.util.config.AppConfig;
import com.app.util.config.IoptsCurl;
import com.frentree.recon.vo.groupall.scheduleCo;
import com.frentree.recon.vo.groupall.schlocationCo;
import com.frentree.recon.vo.groupall.schtargetCo;
import com.google.gson.Gson;
import com.recon.util.database.ibatis.tr.DBInsertTable;
import com.recon.util.database.ibatis.vo.schdelesVo;
import com.recon.util.database.ibatis.vo.schedule_locationVo;

public class ReconSchedulerJobs implements Runnable {
	private DBInsertTable tr = null;
	private static Logger logger = LoggerFactory.getLogger(ReconSchedulerJobs.class);

	private static String customer_id = "";
//	private static int ap_no = 0;
	
	public ReconSchedulerJobs() {
	}

	@Override
	public void run() {

		this.customer_id = AppConfig.getProperty("config.customer");
		
		if("SKT".equals(customer_id)) {
			String str_ap_count = AppConfig.getProperty("config.recon.ap.count");
			int ap_count = ("".equals(str_ap_count)) ? 1 : Integer.parseInt(str_ap_count);
			
			for(int i=0; i<ap_count; i++) {
//				this.ap_no = i;
				executeRun(i);
			}
		} else {
			executeRun(0);
		}
		

	}

	private void executeRun(int ap_no) {
//		String user = AppConfig.getProperty("config.recon.user");
//		String pass = AppConfig.getProperty("config.recon.pawwsord");
		//String ip = AppConfig.getProperty("config.recon.ip");
		String user = (ap_no == 0) ? AppConfig.getProperty("config.recon.user") : AppConfig.getProperty("config.recon.user_"+(ap_no+1));
		String pass = (ap_no == 0) ? AppConfig.getProperty("config.recon.pawwsord") : AppConfig.getProperty("config.recon.pawwsord_"+(ap_no+1));
		String ip = (ap_no == 0) ? AppConfig.getProperty("config.recon.ip") : AppConfig.getProperty("config.recon.ip_"+(ap_no+1)) ;
		String port = AppConfig.getProperty("config.recon.port");
		String api_ver = AppConfig.getProperty("config.recon.api.version");
		String start_date = "";
		String end_date = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		start_date = sdf.format(cal.getTime());
		end_date = sdf.format(new Date());		
		
		String curlurl = String.format("-k -X GET -u %s:%s 'https://%s:%s/%s/schedules?details=true&completed=true&cancelled=true&stopped=true&failed=true&deactivated=true&start_date=%s&limit=5000000&end_date=%s'",
				user, pass, ip, port, api_ver, start_date, end_date);

		logger.info("ReconSchedulerJobs curlurl [" + curlurl + "]");

		String[] array = curlurl.split(" ");

		tr = new DBInsertTable();

		String json_string;
		json_string = new IoptsCurl().opt(array).exec(null);

		if (json_string == null || json_string.length() < 1 || json_string.contains("Resource not found.")) {
			logger.error("Data Null Check IP or ID: " + curlurl);

		} else {

			JSONArray temp1 = new JSONArray(json_string);

			for (int i = 0; i < temp1.length(); i++) {
				Gson gson = new Gson();
				scheduleCo g = gson.fromJson(temp1.get(i).toString(), scheduleCo.class);
				g.setAp_no(ap_no);
				dbjobs(g, ap_no);
			}
		}
	}

	public void dbjobs(scheduleCo g, int ap_no) {
		schdelesVo v = new schdelesVo(g);
		
		// target_id 나 target_name 이 null 으로 들어올 경우 10번 루프 
		for(int i=0; i<10; i++) {
			if(!"".equals(v.getSchedule_target_id()) && v.getSchedule_target_id() != null && !"".equals(v.getSchedule_target_name()) && v.getSchedule_target_name() != null) {
				// id와 name에 빈값이 아닐경우 insert 후 루프 종료
				try {
					tr = new DBInsertTable();
					v.setAp_no(ap_no);
//					logger.info("schedule_target_id :: " + v.getSchedule_target_id());
					tr.setDBInsertTable("insert.setSchedule", v);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		// scanning 상태가 되면 pi_scan_schedule 테이블의 active_status 02로 설정된 값을 01로 변경
		/*if(v.getSchedule_status().equals("scanning")) {
			tr.setDBInsertTable("update.setSchedule", v);
		}*/

		if (g.getTargets() != null) {
			for (schtargetCo t : g.getTargets()) {
				if (t.getLocations() != null) {
					for (schlocationCo sloc : t.getLocations()) {
						schedule_locationVo slocvo = new schedule_locationVo(sloc, g.getId(), t.getId());
						try {
							slocvo.setAp_no(g.getAp_no());
							tr.setDBInsertTable("insert.setSchLocation", slocvo);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
