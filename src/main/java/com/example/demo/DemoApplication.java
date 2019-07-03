package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicLong;



@SpringBootApplication
@RestController
public class DemoApplication {

	private String Amount;
	private String SellerOrderId;
	private String Signature;
	private String TransactionId;
	private String TRANSACTION_ID_TYPE;



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/sendotp")
	public String hello() {
		return "hello world!";
	}

	@GetMapping("/")
	public String greet() {
		return "Hello World";
	}

	private static final String template = "%s";
	private final AtomicLong counter = new AtomicLong();



	@GetMapping("/signin")
	public String Signin(@RequestParam String orderTotalCurrencyCode, @RequestParam String sellerNote, @RequestParam String orderTotalAmount, @RequestParam String customInformation, @RequestParam String sellerStoreName, @RequestParam String sellerOrderId) {
		Amount = orderTotalAmount;
		SellerOrderId = sellerOrderId;
		Signin s = new Signin();
		s.method(Amount, SellerOrderId);

		return s.getResponseData();
	}





	@RequestMapping ("/VerifySignature")
	public boolean VerifySignature(  HttpServletRequest request,  HttpServletResponse response){
		VerifySignature V = new VerifySignature();
		return V.verifyResponse(request,response);

	}




	@GetMapping("/signAndEncryptForOperation")
	public String signAndEncryptForOperation(HttpServletRequest request,  HttpServletResponse response){

		signAndEncryptForOperation S = new signAndEncryptForOperation();

		return S.signAndEncrypt(request,response);
	}






}