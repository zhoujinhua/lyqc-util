package com.liyun.car.common.entity;

import java.io.Serializable;

public class Dict implements Serializable{

	public String name;
	public String code;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
