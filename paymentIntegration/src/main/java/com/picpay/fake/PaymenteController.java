package com.picpay.fake;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymenteController
{
	@RequestMapping("/link")
	public ResponseEntity<String> paymentLink(@RequestParam String value)
	{
		return ResponseEntity.ok("https://www.fakepicpay.com.br/payment/link?value=" + value);
	}
}
