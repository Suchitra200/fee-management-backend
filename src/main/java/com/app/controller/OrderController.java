package com.app.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.config.RazorPayClientConfig;
import com.app.entity.ApiResponse;
import com.app.entity.OrderRequest;
import com.app.entity.OrderResponse;
import com.app.entity.PaymentResponse;
import com.app.service.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

/**
 * 
 * @author Chinna
 */

@RestController
public class OrderController {

	private RazorpayClient client;

	private RazorPayClientConfig razorPayClientConfig;

	@Autowired
	private OrderService orderService;

	@Autowired
	public OrderController(RazorPayClientConfig razorpayClientConfig) throws RazorpayException {
		this.razorPayClientConfig = razorpayClientConfig;
		this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
	}

	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse razorPay = null;
		try {
			System.out.println("entering the method");
			// The transaction amount is expressed in the currency subunit, such
			// as paise (in case of INR)
			String amountInPaise = convertRupeeToPaise(orderRequest.getAmount());
			// Create an order in RazorPay and get the order id
			Order order = createRazorPayOrder(amountInPaise);
			System.out.println(order);
			razorPay = getOrderResponse((String) order.get("id"), amountInPaise);
			// Save order in the database
			orderService.saveOrder(razorPay.getRazorpayOrderId(), 1L);
		} catch (RazorpayException e) {

			return new ResponseEntity<>(new ApiResponse(false, "Error while create payment order: " + e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
		return ResponseEntity.ok(razorPay);
	}

	@PutMapping("/order")
	public ResponseEntity<?> updateOrder(@RequestBody PaymentResponse paymentResponse) {
		String errorMsg = orderService.validateAndUpdateOrder(paymentResponse.getRazorpayOrderId(),
				paymentResponse.getRazorpayPaymentId(), paymentResponse.getRazorpaySignature(),
				razorPayClientConfig.getSecret());
		if (errorMsg != null) {
			return new ResponseEntity<>(new ApiResponse(false, errorMsg), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new ApiResponse(true, paymentResponse.getRazorpayPaymentId()));
	}

	private OrderResponse getOrderResponse(String orderId, String amountInPaise) {
		OrderResponse razorPay = new OrderResponse();
		razorPay.setApplicationFee(amountInPaise);
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(razorPayClientConfig.getKey());
		return razorPay;
	}

	private Order createRazorPayOrder(String amount) throws RazorpayException {
		System.out.println("create razor pay order");
		RazorpayClient razorpayClient = new RazorpayClient("rzp_live_ASOD5nE9paIWXV", "FR453NUT7x6wYNl5cGwz9pDN");

		JSONObject options = new JSONObject();
		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.Orders.create(options);
		return order;
	}

	private String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
	}
}