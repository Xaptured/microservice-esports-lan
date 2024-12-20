package com.esportslan.microservices.esportslanapi.clienthelpers;

import com.esportslan.microservices.esportslanapi.clients.PhonePeClient;
import com.esportslan.microservices.esportslanapi.exceptions.BadRequestErrorException;
import com.esportslan.microservices.esportslanapi.utilities.PhonePeClientCreator;
import com.esportslan.microservices.esportslanapi.utilities.PropertiesReader;
import com.esportslan.microservices.esportslanapi.utilities.StringConstants;
import com.phonepe.sdk.pg.common.http.PhonePeException;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.PhonePePaymentClient;
import com.phonepe.sdk.pg.payments.v1.models.request.PgRefundRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PgRefundResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhonePeClientHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhonePeClientHelper.class);

    @Autowired
    private PhonePeClient phonePeClient;

    public String initiatePayment(String merchantTransactionId, long amount, String callbackURL, String merchantUserId, String redirectURL, String redirectMode) {
        try {
            return phonePeClient.initiatePayment(merchantTransactionId, amount, callbackURL, merchantUserId, redirectURL, redirectMode);
        } catch (PhonePeException exception) {
            LOGGER.error("Got exception while initiating payment with phonepe");
            throw new PhonePeException("Got exception while initiating payment with phonepe: " + exception.getMessage(), exception.getResponseCode(), exception.getPhonePeResponse());
        }
    }

    public String refund(String merchantTransactionId, long amount, String originalTransactionId) {
        try {
            return phonePeClient.refund(merchantTransactionId, amount, originalTransactionId);
        } catch (PhonePeException exception) {
            LOGGER.error("Got exception while refund payment with phonepe");
            throw new PhonePeException("Got exception while refund payment with phonepe: " + exception.getMessage(), exception.getResponseCode(), exception.getPhonePeResponse());
        }
    }

    public PhonePeResponse<PgTransactionStatusResponse> checkStatus(String merchantTransactionId) {
        try {
            return phonePeClient.checkStatus(merchantTransactionId);
        } catch (PhonePeException exception) {
            LOGGER.error("Got exception while checking payment status");
            throw new PhonePeException("Got exception while checking payment status: " + exception.getMessage(), exception.getResponseCode(), exception.getPhonePeResponse());
        }
    }
}
