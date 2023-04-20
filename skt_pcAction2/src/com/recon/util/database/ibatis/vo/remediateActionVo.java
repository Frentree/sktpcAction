package com.recon.util.database.ibatis.vo;

import java.sql.Date;

public class remediateActionVo {
	
	private int index;
	private int action;
	private String target_id;
	private int ap_no;
	private String hostname;
	private String name;
	private String path_orig;
	private String path;
	private String hash_id;
	private String type;
	private String category_no;
	private int done;
	private int success;
	private String result;
	private Date regdate;
	private String remediation_chk;
	
	public remediateActionVo() {
		// TODO Auto-generated constructor stub
	}

	public remediateActionVo(int index, int action, String target_id, int ap_no, String hostname, String name,
			String path_orig, String path, String hash_id, String type, String category_no, int done, int success,
			String result, Date regdate, String remediation_chk) {
		super();
		this.index = index;
		this.action = action;
		this.target_id = target_id;
		this.ap_no = ap_no;
		this.hostname = hostname;
		this.name = name;
		this.path_orig = path_orig;
		this.path = path;
		this.hash_id = hash_id;
		this.type = type;
		this.category_no = category_no;
		this.done = done;
		this.success = success;
		this.result = result;
		this.regdate = regdate;
		this.remediation_chk = remediation_chk;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getHash_id() {
		return hash_id;
	}

	public void setHash_id(String hash_id) {
		this.hash_id = hash_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory_no() {
		return category_no;
	}

	public void setCategory_no(String category_no) {
		this.category_no = category_no;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRemediation_chk() {
		return remediation_chk;
	}

	public void setRemediation_chk(String remediation_chk) {
		this.remediation_chk = remediation_chk;
	}

	@Override
	public String toString() {
		return "remediateActionVo [index=" + index + ", action=" + action + ", target_id=" + target_id + ", ap_no="
				+ ap_no + ", hostname=" + hostname + ", name=" + name + ", path_orig=" + path_orig + ", path=" + path
				+ ", hash_id=" + hash_id + ", type=" + type + ", category_no=" + category_no + ", done=" + done
				+ ", success=" + success + ", result=" + result + ", regdate=" + regdate + ", remediation_chk="
				+ remediation_chk + "]";
	}
		
}
