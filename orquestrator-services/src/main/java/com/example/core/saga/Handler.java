package com.example.core.saga;

import static com.example.core.enums.EEventSource.ORQUESTRATOR;
import static com.example.core.enums.EEventSource.PAYMENT_SERVICE;
import static com.example.core.enums.EStatus.FAIL;
import static com.example.core.enums.EStatus.ROLLBACK_PENDING;
import static com.example.core.enums.EStatus.SUCCESS;
import static com.example.core.enums.ETopic.FINISH_FAIL;
import static com.example.core.enums.ETopic.FINISH_SUCCESS;
import static com.example.core.enums.ETopic.PAYMENT_FAIL;
import static com.example.core.enums.ETopic.PAYMENT_SUCCESS;

public final class Handler {
    private Handler(){

    }

    public static final Object[][] HANDLER = {
            {ORQUESTRATOR, SUCCESS, PAYMENT_SUCCESS},
            {ORQUESTRATOR, FAIL, FINISH_FAIL},

            {PAYMENT_SERVICE, ROLLBACK_PENDING, PAYMENT_FAIL},
            {PAYMENT_SERVICE, FAIL, FINISH_FAIL},
            {PAYMENT_SERVICE, SUCCESS, FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;

}
