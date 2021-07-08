package com.example.his_test1.entity;

import java.util.Date;

import lombok.Data;

@Data
public class HerbalPresctription {

	private int id;
	private int medicalID;
	private int registID;
	private int doctorID;
	private String prescriptionName;
	private Date creationTime;
	private String prescriptionType;
	private short payNumber;
	private String frequency;
	private String drugsUsage;
	private String therapy;
	private String detailed;
	private String advice;
	private byte state;
	
}
