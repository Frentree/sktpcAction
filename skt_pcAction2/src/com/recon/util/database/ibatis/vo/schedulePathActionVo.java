package com.recon.util.database.ibatis.vo;

public class schedulePathActionVo {
	
	private String hash_id;
	private String fid;
	private String target_id;
	private String name;
	private int	ap_no;
	private String path;
	private int	count;
	private int success;
	private int action;
	private int type1;
	private int type2;
	private int type3;
	private int type4;
	private int type5;
	private int type6;
	private int type7;
	private int type8;
	private int type;
	private String file;
	
	public String getHash_id() {
		return hash_id;
	}
	public void setHash_id(String hash_id) {
		this.hash_id = hash_id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAp_no() {
		return ap_no;
	}
	public void setAp_no(int ap_no) {
		this.ap_no = ap_no;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getType1() {
		return type1;
	}
	public void setType1(int type1) {
		this.type1 = type1;
	}
	public int getType2() {
		return type2;
	}
	public void setType2(int type2) {
		this.type2 = type2;
	}
	public int getType3() {
		return type3;
	}
	public void setType3(int type3) {
		this.type3 = type3;
	}
	public int getType4() {
		return type4;
	}
	public void setType4(int type4) {
		this.type4 = type4;
	}
	public int getType5() {
		return type5;
	}
	public void setType5(int type5) {
		this.type5 = type5;
	}
	public int getType6() {
		return type6;
	}
	public void setType6(int type6) {
		this.type6 = type6;
	}
	public int getType7() {
		return type7;
	}
	public void setType7(int type7) {
		this.type7 = type7;
	}
	public int getType8() {
		return type8;
	}
	public void setType8(int type8) {
		this.type8 = type8;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "schedulePathActionVo [hash_id=" + hash_id + ", fid=" + fid + ", target_id=" + target_id + ", name="
				+ name + ", ap_no=" + ap_no + ", path=" + path + ", count=" + count + ", success=" + success
				+ ", action=" + action + ", type1=" + type1 + ", type2=" + type2 + ", type3=" + type3 + ", type4="
				+ type4 + ", type5=" + type5 + ", type6=" + type6 + ", type7=" + type7 + ", type8=" + type8 + ", type="
				+ type + "]";
	}
	
}
