package com.esportslan.microservices.esportslanapi.controllers;

import com.esportslan.microservices.esportslanapi.enums.PaymentStatus;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.PaymentInitiationRequest;
import com.esportslan.microservices.esportslanapi.models.PaymentInitiationResponse;
import com.esportslan.microservices.esportslanapi.models.PaymentStatusResponse;
import com.esportslan.microservices.esportslanapi.services.EventService;
import com.esportslan.microservices.esportslanapi.services.PhonePeService;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "PaymentGateway", description = "Event management APIs for payment gateway")
@RestController
@RequestMapping("/payment-gateway")
public class PaymentGatewayController {
    @Autowired
    private PhonePeService phonePeService;
    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Initiate payment",
            description = "Initiate payment"
    )
    @PostMapping("/initiate")
    public ResponseEntity<PaymentInitiationResponse> initiatePayment(@RequestBody PaymentInitiationRequest request) {
        String url = phonePeService.initiatePayment(request);
        return ResponseEntity.status(HttpStatus.OK).body(new PaymentInitiationResponse(url));
    }

    @Operation(
            summary = "Check payment status",
            description = "Check payment status"
    )
    @PostMapping("/check-status")
    public ResponseEntity<PaymentStatusResponse> checkStatus(@RequestParam Map<String, String> request) {
        PhonePeResponse<PgTransactionStatusResponse> statusResponse = phonePeService.checkStatus(request.get("transactionId"));
        Audience audience = eventService.fetchInitiatePayment(request.get("transactionId"));

        if (statusResponse.getCode().equals("BAD_REQUEST") || statusResponse.getCode().equals("AUTHORIZATION_FAILED") ||
                statusResponse.getCode().equals("PAYMENT_DECLINED") || statusResponse.getCode().equals("TRANSACTION_NOT_FOUND") ||
                statusResponse.getCode().equals("PAYMENT_ERROR") || statusResponse.getCode().equals("TIMED_OUT")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentStatusResponse(PaymentStatus.FAILED));
        }

        if (PaymentStatus.COMPLETED.toString().equals(statusResponse.getData().getState())) {
            audience.setStatus(PaymentStatus.COMPLETED);
            eventService.saveOrUpdateAudience(audience);

            return ResponseEntity.status(HttpStatus.OK).body(new PaymentStatusResponse(PaymentStatus.COMPLETED));
        }
        else if (statusResponse.getCode().equals("INTERNAL_SERVER_ERROR") || PaymentStatus.PENDING.toString().equals(statusResponse.getData().getState())) {
            audience.setStatus(PaymentStatus.PENDING);
            eventService.savePendingPayment(audience);

            return ResponseEntity.status(HttpStatus.OK).body(new PaymentStatusResponse(PaymentStatus.PENDING));
        }
        else {
            audience.setStatus(PaymentStatus.FAILED);
            eventService.saveFailedPayment(audience);

            return ResponseEntity.status(HttpStatus.OK).body(new PaymentStatusResponse(PaymentStatus.FAILED));
        }
    }

    @Operation(
            summary = "Refund payment",
            description = "Refund payment"
    )
    @PostMapping("/refund")
    public ResponseEntity<String> refundPayment(@RequestBody Audience audience) {
        String code = phonePeService.refundPayment(audience);
        return ResponseEntity.status(HttpStatus.OK).body(code);
    }
}
