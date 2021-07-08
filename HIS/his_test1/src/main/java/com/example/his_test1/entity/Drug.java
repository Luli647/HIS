package com.example.his_test1.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class Drug {
	@JSONField(name = "id")
	private int id;
	@JSONField(name = "code")
	private String code;
	@JSONField(name = "name")
	private String name;
	@JSONField(name = "format")
	private String format;
	@JSONField(name = "unit")
	private String unit;
	@JSONField(name = "manufacturer")
	private String manufacturer;
	@JSONField(name = "dosageid")
	private String dosageid;
	@JSONField(name = "typeid")
	private String typeid;
	@JSONField(name = "price")
	private float price;
	@JSONField(name = "mnemonicCode")
	private String mnemonicCode;
	@JSONField(name = "creationDate", format="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	@JSONField(name = "lastUpdateDate", format="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	
	public Drug() {
		creationDate = new Date();
	}
	
}
