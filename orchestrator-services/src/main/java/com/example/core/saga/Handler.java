package com.example.core.saga;

import static com.example.core.enums.EEventSource.ORCHESTRATOR;
import static com.example.core.enums.EEventSource.PAYMENT_SERVICE;
import static com.example.core.enums.EStatus.FAIL;
import static com.example.core.enums.EStatus.ROLLBACK_PENDING;
import static com.example.core.enums.EStatus.SUCCESS;
import static com.example.core.enums.ETopic.*;

public final class Handler {
    private Handler(){}

    public static final Object[][] HANDLER = {
            {ORCHESTRATOR, SUCCESS, PRODUCT_VALIDATION_SUCCESS},
            {ORCHESTRATOR, FAIL, FINISH_FAIL},

            {PRODUCT_VALIDATION_SUCCESS, SUCCESS, PAYMENT_SUCCESS},
            {PRODUCT_VALIDATION_SUCCESS, FAIL, FINISH_FAIL},
            {PRODUCT_VALIDATION_SUCCESS, ROLLBACK_PENDING, PRODUCT_VALIDATION_SUCCESS},

            {PAYMENT_SERVICE, ROLLBACK_PENDING, PAYMENT_FAIL},
            {PAYMENT_SERVICE, FAIL, PRODUCT_VALIDATION_SUCCESS},
            {PAYMENT_SERVICE, SUCCESS, FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;

}
