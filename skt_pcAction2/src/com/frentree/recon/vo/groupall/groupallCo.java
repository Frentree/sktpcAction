package com.frentree.recon.vo.groupall;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class groupallCo {
	@SerializedName("comments")
	private String  comments;
	
	@SerializedName("search_status")
	private String  search_status;
	
	@SerializedName("name")
	private String  name;
	
	@SerializedName("id")
	private String  id;

	@SerializedName("matches")	
	private matchesCo matches;
	
	@SerializedName("targets")
	private List<targetslCo> targets;
	
	@SerializedName("errors")
	private errorsCo errors;

	
	@SerializedName("search_time")
	private String search_time;


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getSearch_status() {
		return search_status;
	}


	public void setSearch_status(String search_status) {
		this.search_status = search_status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public matchesCo getMatches() {
		return matches;
	}


	public void setMatches(matchesCo matches) {
		this.matches = matches;
	}


	public List<targetslCo> getTargets() {
		return targets;
	}


	public void setTargets(List<targetslCo> targets) {
		this.targets = targets;
	}


	public errorsCo getErrors() {
		return errors;
	}


	public void setErrors(errorsCo errors) {
		this.errors = errors;
	}


	public String getSearch_time() {
		return search_time;
	}


	public void setSearch_time(String search_time) {
		this.search_time = search_time;
	}
	
}
