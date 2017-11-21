package com.srivastava.dto;

public class MasterDTO {
	private String desc;
	private String name;
	public MasterDTO(String name , String desc){
		this.name = name;
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
