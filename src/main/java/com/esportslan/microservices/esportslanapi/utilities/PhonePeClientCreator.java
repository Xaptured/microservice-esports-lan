package com.esportslan.microservices.esportslanapi.utilities;

import com.phonepe.sdk.pg.Env;
import com.phonepe.sdk.pg.payments.v1.PhonePePaymentClient;
import org.springframework.stereotype.Service;

@Service
public class PhonePeClientCreator {

    private static final String PHONE_PAY_MERCHANT_ID = PropertiesReader.getProperty(StringConstants.PHONE_PAY_MERCHANT_ID);
    private static final String PHONE_PAY_SALT_KEY = PropertiesReader.getProperty(StringConstants.PHONE_PAY_SALT_KEY);
    private static final Integer PHONE_PAY_KEY_INDEX = Integer.parseInt(PropertiesReader.getProperty(StringConstants.PHONE_PAY_KEY_INDEX));
    private static final Env environment = Env.UAT;
    private static final boolean SHOULD_PUBLISH_EVENTS = true;

    public PhonePePaymentClient create() {
        return new PhonePePaymentClient(PHONE_PAY_MERCHANT_ID, PHONE_PAY_SALT_KEY, PHONE_PAY_KEY_INDEX, environment, SHOULD_PUBLISH_EVENTS);
    }
}
