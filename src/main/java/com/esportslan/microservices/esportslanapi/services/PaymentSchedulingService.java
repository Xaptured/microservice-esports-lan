package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.enums.PaymentStatus;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSchedulingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentSchedulingService.class);

    @Autowired
    private EventService eventService;
    @Autowired
    private PhonePeService phonePeService;

    @Scheduled(cron = "0 */20 * * * *")
    public void processPendingPayments() {
        List<Audience> pendingAudiences = eventService.fetchAllPendingPayments();
        if (pendingAudiences != null && !pendingAudiences.isEmpty()) {
            for (Audience pendingAudience : pendingAudiences) {
                PhonePeResponse<PgTransactionStatusResponse> statusResponse
                        = phonePeService.checkStatus(pendingAudience.getMerchantTransactionId());

                if (PaymentStatus.COMPLETED.toString().equals(statusResponse.getData().getState())) {
                    if (!pendingAudience.isRefund()) {
                        pendingAudience.setStatus(PaymentStatus.COMPLETED);
                        eventService.saveOrUpdateAudience(pendingAudience);
                    }
                    eventService.deletePendingPayment(pendingAudience.getEmail(), pendingAudience.getEventName());
                    LOGGER.info("PaymentScheduling: Pending audience is completed for event: {} with email: {} and transactionId: {}", pendingAudience.getEventName(), pendingAudience.getEmail(), pendingAudience.getMerchantTransactionId());
                } else if (statusResponse.getCode().equals("INTERNAL_SERVER_ERROR") || PaymentStatus.PENDING.toString().equals(statusResponse.getData().getState())) {
                    LOGGER.info("PaymentScheduling: Still in pending for event: {} with email: {} and transactionId: {}", pendingAudience.getEventName(), pendingAudience.getEmail(), pendingAudience.getMerchantTransactionId());
                } else {
                    pendingAudience.setStatus(PaymentStatus.FAILED);
                    eventService.saveFailedPayment(pendingAudience);
                    eventService.deletePendingPayment(pendingAudience.getEmail(), pendingAudience.getEventName());
                }
            }
        } else {
            LOGGER.info("PaymentScheduling: No pending payments for audience");
        }
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void processFailedPayments() {
        List<Audience> failedAudiences = eventService.fetchAllFailedPayments();
        if (failedAudiences != null && !failedAudiences.isEmpty()) {
            for (Audience failedAudience : failedAudiences) {
                String state = phonePeService.refundPayment(failedAudience);

                if (PaymentStatus.COMPLETED.toString().equals(state)) {
                    LOGGER.info("PaymentScheduling: Failed audience refund is completed for event: {} with email: {} and transactionId: {}", failedAudience.getEventName(), failedAudience.getEmail(), failedAudience.getMerchantTransactionId());
                    eventService.deleteFailedPayment(failedAudience.getEmail(), failedAudience.getEventName());
                } else if (PaymentStatus.PENDING.toString().equals(state)) {
                    LOGGER.info("PaymentScheduling: Failed audience refund in pending for event: {} with email: {} and transactionId: {}", failedAudience.getEventName(), failedAudience.getEmail(), failedAudience.getMerchantTransactionId());
                    failedAudience.setRefund(true);
                    eventService.savePendingPayment(failedAudience);
                    eventService.deleteFailedPayment(failedAudience.getEmail(), failedAudience.getEventName());
                } else {
                    LOGGER.info("PaymentScheduling: Failed audience refund in failed for event: {} with email: {} and transactionId: {}", failedAudience.getEventName(), failedAudience.getEmail(), failedAudience.getMerchantTransactionId());
                }
            }
        } else {
            LOGGER.info("PaymentScheduling: No failed payments for audience");
        }
    }

}
