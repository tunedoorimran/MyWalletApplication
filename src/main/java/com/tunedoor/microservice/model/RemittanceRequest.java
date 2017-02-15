package com.tunedoor.microservice.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Mohamed Saeed
 *
 */
@Builder
@Data
public class RemittanceRequest implements Serializable{
	private static final long serialVersionUID = 6083243525217515867L;
	private long senderId;
	private long beneficiaryId;
	private int amount;
}
