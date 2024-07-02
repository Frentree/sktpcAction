package com.recon.util.database.ibatis.vo;

public class piCustomPatternVo {
	
	private Integer PATTERN_IDX;
	private String PATTERN_CODE;
	private String PATTERN_KR_NAME;
	
	public Integer getPATTERN_IDX() {
		return PATTERN_IDX;
	}
	public void setPATTERN_IDX(Integer pATTERN_IDX) {
		PATTERN_IDX = pATTERN_IDX;
	}
	public String getPATTERN_CODE() {
		return PATTERN_CODE;
	}
	public void setPATTERN_CODE(String pATTERN_CODE) {
		PATTERN_CODE = pATTERN_CODE;
	}
	public String getPATTERN_KR_NAME() {
		return PATTERN_KR_NAME;
	}
	public void setPATTERN_KR_NAME(String pATTERN_KR_NAME) {
		PATTERN_KR_NAME = pATTERN_KR_NAME;
	}
	
	@Override
	public String toString() {
		return "piCustomPatternVo [PATTERN_IDX=" + PATTERN_IDX + ", PATTERN_CODE=" + PATTERN_CODE + ", PATTERN_KR_NAME="
				+ PATTERN_KR_NAME + "]";
	}
	
}

