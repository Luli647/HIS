package com.example.his_test1.entity;

import java.util.Date;

import lombok.Data;


@Data
public class PatientCost {
	private int id;
	private int registID;
	private int invoiceID;
	private int itemID;
	private int itemType;
	private String name;
	private float price;
	private float amount;
	private int depID;
	private Date createTime;
	private int createOperId;
	private Date payTime;
	private int registerID;
	private int feeType;
	private int backID;
}
