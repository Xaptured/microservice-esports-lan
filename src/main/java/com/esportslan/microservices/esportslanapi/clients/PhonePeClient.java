package com.esportslan.microservices.esportslanapi.clients;

import com.esportslan.microservices.esportslanapi.utilities.PhonePeClientCreator;
import com.esportslan.microservices.esportslanapi.utilities.PropertiesReader;
import com.esportslan.microservices.esportslanapi.utilities.StringConstants;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.PhonePePaymentClient;
import com.phonepe.sdk.pg.payments.v1.models.request.PgPayRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PayPageInstrumentResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgPayResponse;
import com.phonepe.sdk.pg.payments.v1.models.request.PgRefundRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PgRefundResponse;
import org.springframework.stereotype.Service;

@Service
public class PhonePeClient {

    public String initiatePayment(String merchantTransactionId, long amount, String callbackURL, String merchantUserId, String redirectURL, String redirectMode) {
        PhonePeClientCreator clientCreator = new PhonePeClientCreator();
        PhonePePaymentClient phonePePaymentClient = clientCreator.create();
        long paiseAmount = amount * 100;

        PgPayRequest pgPayRequest = PgPayRequest.PayPagePayRequestBuilder()
                .amount(paiseAmount)
                .merchantId(PropertiesReader.getProperty(StringConstants.PHONE_PAY_MERCHANT_ID))
                .merchantTransactionId(merchantTransactionId)
                .callbackUrl(callbackURL)
                .merchantUserId(merchantUserId)
                .redirectUrl(redirectURL)
                .redirectMode(redirectMode)
                .build();

        PhonePeResponse<PgPayResponse> payResponse = phonePePaymentClient.pay(pgPayRequest);

        PayPageInstrumentResponse payPageInstrumentResponse = (PayPageInstrumentResponse) payResponse.getData().getInstrumentResponse();
        return payPageInstrumentResponse.getRedirectInfo().getUrl();
    }

    public PhonePeResponse<PgTransactionStatusResponse> checkStatus(String merchantTransactionId) {
        PhonePeClientCreator clientCreator = new PhonePeClientCreator();
        PhonePePaymentClient phonePePaymentClient = clientCreator.create();

        PhonePeResponse<PgTransactionStatusResponse> statusResponse = phonePePaymentClient.checkStatus(merchantTransactionId);
        return statusResponse;
    }

    public String refund(String merchantTransactionId, long amount, String originalTransactionId) {
        PhonePeClientCreator clientCreator = new PhonePeClientCreator();
        PhonePePaymentClient phonePePaymentClient = clientCreator.create();

        PgRefundRequest pgRefundRequest = PgRefundRequest.builder()
                .amount(amount)
                .merchantId(PropertiesReader.getProperty(StringConstants.PHONE_PAY_MERCHANT_ID))
                .merchantTransactionId(merchantTransactionId)
                .originalTransactionId(originalTransactionId)
                .build();
        PhonePeResponse<PgRefundResponse> refundResponse = phonePePaymentClient.refund(pgRefundRequest);
        return refundResponse.getData().getState();
    }
}
