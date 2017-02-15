package com.tunedoor.microservice.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tunedoor.microservice.model.RemittanceRequest;
import com.tunedoor.microservice.model.TransferRequest;
import com.tunedoor.microservice.model.User;

/**
 * 
 * @author Mohamed Saeed
 *
 */
@RestController
public class MyWalletController {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@Value("${input.user_service}")
	private String findUserServiceUrl;
	@Value("${input.balance_service}")
	private String checkUserBalanceServiceUrl;
	@Value("${input.transfer_service}")
	private String doTransferServiceUrl;

	/**
	 * 
	 * @param remittanceRequest
	 * @return TransactionId
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/financial/remittance")
	public String remittance(@RequestBody RemittanceRequest remittanceRequest) {
		User sender = restTemplate.getForObject(findUserServiceUrl, User.class, remittanceRequest.getSenderId());
		User receiver = restTemplate.getForObject(findUserServiceUrl, User.class, remittanceRequest.getBeneficiaryId());
		TransferRequest transferRequest = TransferRequest.builder().senderId(sender.getId())
				.beneficiaryId(receiver.getId()).amount(remittanceRequest.getAmount()).build();
		return restTemplate.postForObject(doTransferServiceUrl, transferRequest, String.class);
	}

	/**
	 * 
	 * @param userId
	 * @return Balance
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/financial/balance/{id}")
	public int checkBalance(@PathVariable(name = "id") long userId) {
		User user = restTemplate.getForObject(findUserServiceUrl, User.class, userId);
		return restTemplate.getForObject(checkUserBalanceServiceUrl, Integer.class, user.getId());
	}
}
