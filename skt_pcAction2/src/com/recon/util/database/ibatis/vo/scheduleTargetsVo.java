package com.recon.util.database.ibatis.vo;

import java.util.Arrays;
import java.util.List;

import com.recon.util.database.ibatis.vo.piCustomPatternVo;

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
	private String type1_cnt;
	private String type2_cnt;
	private String type3_cnt;
	private String type4_cnt;
	private String type5_cnt;
	private String type6_cnt;
	private String type7_cnt;
	private String type8_cnt;
	private String type9_cnt;
	private String type10_cnt;
	private String enable;
	
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
	public String getType1_cnt() {
		return type1_cnt;
	}
	public void setType1_cnt(String type1_cnt) {
		this.type1_cnt = type1_cnt;
	}
	public String getType2_cnt() {
		return type2_cnt;
	}
	public void setType2_cnt(String type2_cnt) {
		this.type2_cnt = type2_cnt;
	}
	public String getType3_cnt() {
		return type3_cnt;
	}
	public void setType3_cnt(String type3_cnt) {
		this.type3_cnt = type3_cnt;
	}
	public String getType4_cnt() {
		return type4_cnt;
	}
	public void setType4_cnt(String type4_cnt) {
		this.type4_cnt = type4_cnt;
	}
	public String getType5_cnt() {
		return type5_cnt;
	}
	public void setType5_cnt(String type5_cnt) {
		this.type5_cnt = type5_cnt;
	}
	public String getType6_cnt() {
		return type6_cnt;
	}
	public void setType6_cnt(String type6_cnt) {
		this.type6_cnt = type6_cnt;
	}
	public String getType7_cnt() {
		return type7_cnt;
	}
	public void setType7_cnt(String type7_cnt) {
		this.type7_cnt = type7_cnt;
	}
	public String getType8_cnt() {
		return type8_cnt;
	}
	public void setType8_cnt(String type8_cnt) {
		this.type8_cnt = type8_cnt;
	}
	public String getType9_cnt() {
		return type9_cnt;
	}
	public void setType9_cnt(String type9_cnt) {
		this.type9_cnt = type9_cnt;
	}
	public String getType10_cnt() {
		return type10_cnt;
	}
	public void setType10_cnt(String type10_cnt) {
		this.type10_cnt = type10_cnt;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	@Override
	public String toString() {
		return "scheduleTargetsVo [target_id=" + target_id + ", ap_no=" + ap_no + ", name=" + name + ", schedule_id="
				+ schedule_id + ", policy_id=" + policy_id + ", policy_name=" + policy_name + ", action=" + action
				+ ", action_nm=" + action_nm + ", schedule_time=" + schedule_time + ", std_id=" + std_id
				+ ", type1_cnt=" + type1_cnt + ", type2_cnt=" + type2_cnt + ", type3_cnt=" + type3_cnt + ", type4_cnt="
				+ type4_cnt + ", type5_cnt=" + type5_cnt + ", type6_cnt=" + type6_cnt + ", type7_cnt=" + type7_cnt
				+ ", type8_cnt=" + type8_cnt + ", type9_cnt=" + type9_cnt + ", type10_cnt=" + type10_cnt + ", enable="
				+ enable + "]";
	}
	
}
