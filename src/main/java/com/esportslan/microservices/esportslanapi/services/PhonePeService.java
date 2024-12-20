package com.esportslan.microservices.esportslanapi.services;

import com.esportslan.microservices.esportslanapi.clienthelpers.PhonePeClientHelper;
import com.esportslan.microservices.esportslanapi.clients.PhonePeClient;
import com.esportslan.microservices.esportslanapi.models.Audience;
import com.esportslan.microservices.esportslanapi.models.PaymentInitiationRequest;
import com.esportslan.microservices.esportslanapi.utilities.PropertiesReader;
import com.esportslan.microservices.esportslanapi.utilities.StringConstants;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PhonePeService {

    @Autowired
    private PhonePeClientHelper phonePeClientHelper;
    @Autowired
    private EventService eventService;

    public String initiatePayment(PaymentInitiationRequest request) {
        String merchantTransactionId = UUID.randomUUID().toString().substring(0,34);
        String callbackURL = PropertiesReader.getProperty(StringConstants.PHONE_PAY_INITIATE_CALLBACK);
        String redirectURL = PropertiesReader.getProperty(StringConstants.PHONE_PAY_INITIATE_CALLBACK);
        String redirectMode = PropertiesReader.getProperty(StringConstants.PHONE_PAY_REDIRECT_MODE);
        String url = phonePeClientHelper.initiatePayment(merchantTransactionId, request.getAmount(), callbackURL, request.getName(), redirectURL, redirectMode);

        Audience audience = new Audience();
        audience.setEmail(request.getEmail());
        audience.setName(request.getName());
        audience.setEventName(request.getEventName());
        audience.setMerchantTransactionId(merchantTransactionId);
        audience.setAmount(BigDecimal.valueOf(request.getAmount()));

        eventService.saveInitiatePayment(audience);

        return url;
    }

    public PhonePeResponse<PgTransactionStatusResponse> checkStatus(String merchantTransactionId) {
        return phonePeClientHelper.checkStatus(merchantTransactionId);
    }

    public String refundPayment(Audience audience) {
        String merchantTransactionId = UUID.randomUUID().toString().substring(0,34);
        long amount = audience.getAmount().longValue();
        String originalTransactionId = audience.getMerchantTransactionId();
        phonePeClientHelper.refund(merchantTransactionId, amount, originalTransactionId);
        return checkStatus(merchantTransactionId).getData().getState();
    }
}
