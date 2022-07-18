package com.recon.util.database.ibatis.vo;

public class netSchduleStatusVo {
	private String schedule_id = "";
	private int ap_no = 0;

	public netSchduleStatusVo() {

	}
	

	public netSchduleStatusVo(String schedule_id, int ap_no) {
		super();
		this.schedule_id = schedule_id;
		this.ap_no = ap_no;
	}

	public String getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}


	public int getAp_no() {
		return ap_no;
	}

	public void setAp_no(int ap_no) {
		this.ap_no = ap_no;
	}
	
}
