package com.example.his_test1.entity;

import lombok.Data;

@Data
public class PrescriptionDetailed {
	private int id;
	private int prescriptionID;
	private int drugsID;
	private String drugsUsage;
	private String dosage;
	private String frequency;
	private float amount;
	private int state;
	private String drugsName;
	private String drugsFormat;
	private float drugsPrice;
}
