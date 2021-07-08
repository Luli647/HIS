package com.example.his_test1.entity;

import lombok.Data;

@Data
public class HerbalDetailed {
	
	private int id;
	private int herbalPressID;
	private int herbalID;
	private String dosage;
	private float price;
	private String footNote;
}
