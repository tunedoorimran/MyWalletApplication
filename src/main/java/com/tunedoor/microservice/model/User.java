package com.tunedoor.microservice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author Mohamed Saeed
 *
 */
@Data
public class User implements Serializable{

	private static final long serialVersionUID = -3335431292667721811L;
	
	private long id;
	private String firstName;
	private String lastName;
}
