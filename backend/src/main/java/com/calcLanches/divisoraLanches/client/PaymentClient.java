package com.calcLanches.divisoraLanches.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment", url = "http://localhost:8081")
public interface PaymentClient
{
	@PostMapping("/payment/link")
	String generatePaymentLink(@RequestParam String value);
}
