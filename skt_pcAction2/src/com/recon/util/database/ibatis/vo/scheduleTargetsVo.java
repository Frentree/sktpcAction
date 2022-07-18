package com.recon.util.database.ibatis.vo;

import java.util.Arrays;

public class scheduleTargetsVo {
	
	private String target_id;
	private int	ap_no;
	private String name;
	private int	schedule_id;
	private int policy_id;
	private String policy_name;
	private int action;
	private String action_nm;
	private String schedule_time;
	private String std_id;
	private int rrn_cnt;
	private int foreigner_cnt;
	private int driver_cnt;
	private int passport_cnt;
	private int account_cnt;
	private int card_cnt;
	private int phone_cnt;
	private int mobile_phone_cnt;
	private int local_phone_cnt;
	private int email_cnt;
	private String[] fileArray;
	
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
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	public String getPolicy_name() {
		return policy_name;
	}
	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getAction_nm() {
		return action_nm;
	}
	public void setAction_nm(String action_nm) {
		this.action_nm = action_nm;
	}
	public String getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(String schedule_time) {
		this.schedule_time = schedule_time;
	}
	public String getStd_id() {
		return std_id;
	}
	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	public int getRrn_cnt() {
		return rrn_cnt;
	}
	public void setRrn_cnt(int rrn_cnt) {
		this.rrn_cnt = rrn_cnt;
	}
	public int getForeigner_cnt() {
		return foreigner_cnt;
	}
	public void setForeigner_cnt(int foreigner_cnt) {
		this.foreigner_cnt = foreigner_cnt;
	}
	public int getDriver_cnt() {
		return driver_cnt;
	}
	public void setDriver_cnt(int driver_cnt) {
		this.driver_cnt = driver_cnt;
	}
	public int getPassport_cnt() {
		return passport_cnt;
	}
	public void setPassport_cnt(int passport_cnt) {
		this.passport_cnt = passport_cnt;
	}
	public int getAccount_cnt() {
		return account_cnt;
	}
	public void setAccount_cnt(int account_cnt) {
		this.account_cnt = account_cnt;
	}
	public int getCard_cnt() {
		return card_cnt;
	}
	public void setCard_cnt(int card_cnt) {
		this.card_cnt = card_cnt;
	}
	public int getPhone_cnt() {
		return phone_cnt;
	}
	public void setPhone_cnt(int phone_cnt) {
		this.phone_cnt = phone_cnt;
	}
	public int getMobile_phone_cnt() {
		return mobile_phone_cnt;
	}
	public void setMobile_phone_cnt(int mobile_phone_cnt) {
		this.mobile_phone_cnt = mobile_phone_cnt;
	}
	public int getLocal_phone_cnt() {
		return local_phone_cnt;
	}
	public void setLocal_phone_cnt(int local_phone_cnt) {
		this.local_phone_cnt = local_phone_cnt;
	}
	public int getEmail_cnt() {
		return email_cnt;
	}
	public void setEmail_cnt(int email_cnt) {
		this.email_cnt = email_cnt;
	}
	public String[] getFileArray() {
		return fileArray;
	}
	public void setFileArray(String[] fileArray) {
		this.fileArray = fileArray;
	}
	
	@Override
	public String toString() {
		return "scheduleTargetsVo [target_id=" + target_id + ", ap_no=" + ap_no + ", name=" + name + ", schedule_id="
				+ schedule_id + ", policy_id=" + policy_id + ", policy_name=" + policy_name + ", action=" + action
				+ ", action_nm=" + action_nm + ", schedule_time=" + schedule_time + ", std_id=" + std_id + ", rrn_cnt="
				+ rrn_cnt + ", foreigner_cnt=" + foreigner_cnt + ", driver_cnt=" + driver_cnt + ", passport_cnt="
				+ passport_cnt + ", account_cnt=" + account_cnt + ", card_cnt=" + card_cnt + ", phone_cnt=" + phone_cnt
				+ ", mobile_phone_cnt=" + mobile_phone_cnt + ", local_phone_cnt=" + local_phone_cnt + ", email_cnt="
				+ email_cnt + ", fileArray=" + Arrays.toString(fileArray) + "]";
	}
	
}
