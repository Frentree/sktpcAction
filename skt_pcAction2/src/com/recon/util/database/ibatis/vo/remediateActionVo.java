package com.recon.util.database.ibatis.vo;

import java.sql.Date;

public class remediateActionVo {
	
	private int index;
	private int action;
	private String target_id;
	private int ap_no;
	private String hostname;
	private String path_orig;
	private String path;
	private Date regdate;
	
	public remediateActionVo() {
		// TODO Auto-generated constructor stub
	}

	public remediateActionVo(int index, int action, String target_id, int ap_no, String hostname, String path_orig,
			String path, Date regdate) {
		super();
		this.index = index;
		this.action = action;
		this.target_id = target_id;
		this.ap_no = ap_no;
		this.hostname = hostname;
		this.path_orig = path_orig;
		this.path = path;
		this.regdate = regdate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPath_orig() {
		return path_orig;
	}

	public void setPath_orig(String path_orig) {
		this.path_orig = path_orig;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "remediateActionVo [index=" + index + ", action=" + action + ", target_id=" + target_id + ", ap_no="
				+ ap_no + ", hostname=" + hostname + ", path_orig=" + path_orig + ", path=" + path + ", regdate="
				+ regdate + "]";
	}
	
	
	
}
