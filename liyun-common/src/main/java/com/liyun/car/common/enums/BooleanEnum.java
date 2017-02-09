package com.liyun.car.common.enums;

public enum BooleanEnum implements com.liyun.car.common.service.Enum{

	NULL("","--"),
	YES("1","是"),
	NO("0","否");
	
	private String name;
	private String value;
	
	private BooleanEnum(String value,String name){
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
