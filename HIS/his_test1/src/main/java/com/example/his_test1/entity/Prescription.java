package com.example.his_test1.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Prescription {
	private int id;
	private int medicalID;
	private int registID;
	private int userID;
	private String prescriptionName;
	private Date prescriptionTime;
	private int prescriptionState;
	
}
