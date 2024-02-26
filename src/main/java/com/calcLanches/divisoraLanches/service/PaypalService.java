package com.calcLanches.divisoraLanches.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaypalService {

    private final PayPalHttpClient payPalHttpClient;

    public String gerarUrlPaypal(Float total){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amountWithBreakdown =
                new AmountWithBreakdown().currencyCode("BRL").value(total.toString());

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountWithBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("https://localhost:8080/capture")
                .cancelUrl("https://localhost:8080/cancel");
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
//            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
//            Order order = orderHttpResponse.result();

            return order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

        }  catch (IOException e) {
            log.error(e.getMessage());
        }

        return "";
    }

}
