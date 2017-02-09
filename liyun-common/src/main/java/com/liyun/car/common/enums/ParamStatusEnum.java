package com.liyun.car.common.enums;

public enum ParamStatusEnum implements com.liyun.car.common.service.Enum{

	NULL("","--"),
	OFF("0","无效"),
	ON("1","有效");
	
	private String name;
	private String value;
	
	private ParamStatusEnum(String value,String name){
		this.value = value;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
