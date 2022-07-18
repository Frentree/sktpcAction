package com.recon.util.database.ibatis.vo;

public class netScheduleVo {
	
	private String target_id;
	private int ap_no;
	private String name;
	private String location_id;
	private String datatype_id;
	private String datatype_label;
	private int	action;
	private String net_id;
	private int net_type;
	private String schedule_time;
	
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public int getAp_no() {
		return ap_no;
	}
	public void setAp_no(int ap_no) {
		this.ap_no = ap_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getDatatype_id() {
		return datatype_id;
	}
	public void setDatatype_id(String datatype_id) {
		this.datatype_id = datatype_id;
	}
	public String getDatatype_label() {
		return datatype_label;
	}
	public void setDatatype_label(String datatype_label) {
		this.datatype_label = datatype_label;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getNet_id() {
		return net_id;
	}
	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}
	public int getNet_type() {
		return net_type;
	}
	public void setNet_type(int net_type) {
		this.net_type = net_type;
	}
	public String getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(String schedule_time) {
		this.schedule_time = schedule_time;
	}
	
	@Override
	public String toString() {
		return "netScheduleVo [target_id=" + target_id + ", ap_no=" + ap_no + ", name=" + name + ", location_id="
				+ location_id + ", datatype_id=" + datatype_id + ", datatype_label=" + datatype_label + ", action="
				+ action + ", net_id=" + net_id + ", net_type=" + net_type + ", schedule_time=" + schedule_time + "]";
	}

}
