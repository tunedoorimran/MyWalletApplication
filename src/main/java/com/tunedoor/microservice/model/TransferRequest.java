package com.tunedoor.microservice.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Mohamed Saeed
 *
 */
@Data
@Builder
public class TransferRequest implements Serializable {
	private static final long serialVersionUID = 7200260673643495176L;
	
	private long senderId;
	private long beneficiaryId;
	private int amount;
}
