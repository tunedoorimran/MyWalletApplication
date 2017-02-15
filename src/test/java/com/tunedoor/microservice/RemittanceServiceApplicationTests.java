package com.tunedoor.microservice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tunedoor.microservice.apigateway.MyWalletController;
import com.tunedoor.microservice.model.RemittanceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemittanceServiceApplicationTests {

	@BeforeClass
	public static void setConfiguration() {
		System.setProperty("spring.config.name", "mywallet-server");
	}

	@Autowired
	MyWalletController myWalletController;

	@Test
	public void checkBalance() {
		long[] userIds = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (long id : userIds) {
			System.out.println("Balance for user Id: " + id + " is: " + myWalletController.checkBalance(id));
		}
	}

	@Test
	public void remittance() {
		RemittanceRequest remittanceRequest1 = RemittanceRequest.builder().senderId(1).beneficiaryId(2).amount(3)
				.build();
		RemittanceRequest remittanceRequest2 = RemittanceRequest.builder().senderId(3).beneficiaryId(4).amount(30)
				.build();
		RemittanceRequest remittanceRequest3 = RemittanceRequest.builder().senderId(5).beneficiaryId(6).amount(31)
				.build();
		RemittanceRequest remittanceRequest4 = RemittanceRequest.builder().senderId(7).beneficiaryId(8).amount(32)
				.build();
		RemittanceRequest remittanceRequest5 = RemittanceRequest.builder().senderId(9).beneficiaryId(10).amount(23)
				.build();
		System.out.println("Transaction ID:" + myWalletController.remittance(remittanceRequest1));
		System.out.println("Transaction ID:" + myWalletController.remittance(remittanceRequest2));
		System.out.println("Transaction ID:" + myWalletController.remittance(remittanceRequest3));
		System.out.println("Transaction ID:" + myWalletController.remittance(remittanceRequest4));
		System.out.println("Transaction ID:" + myWalletController.remittance(remittanceRequest5));
		long[] userIds = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (long id : userIds) {
			System.out.println("Balance for user Id: " + id + " after remittance is: " + myWalletController.checkBalance(id));
		}
	}

}
