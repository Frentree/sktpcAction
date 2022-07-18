package com.recon.util.database.ibatis.vo;

public class scheduleTomorowActionVo {
	
	private String hash_id;
	private String hostnm;
	private String target_id;
	private int	ap_no;
	private int	action;
	private int	success;
	private String path;
	public String getHash_id() {
		return hash_id;
	}
	public void setHash_id(String hash_id) {
		this.hash_id = hash_id;
	}
	public String getHostnm() {
		return hostnm;
	}
	public void setHostnm(String hostnm) {
		this.hostnm = hostnm;
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
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "scheduleTomorowActionVo [hash_id=" + hash_id + ", hostnm=" + hostnm + ", target_id=" + target_id
				+ ", ap_no=" + ap_no + ", action=" + action + ", path=" + path + "]";
	}
	
}
