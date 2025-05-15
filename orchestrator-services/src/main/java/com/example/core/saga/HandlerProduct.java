package com.example.core.saga;

import static com.example.core.enums.EEventSource.*;
import static com.example.core.enums.EEventSource.ORCHESTRATOR;
import static com.example.core.enums.EEventSource.ORCHESTRATOR_PRODUCT;
import static com.example.core.enums.EStatus.*;
import static com.example.core.enums.ETopic.*;

public final class HandlerProduct {
    private HandlerProduct(){}

    public static final Object[][] HANDLER_PRODUCT = {
            {ORCHESTRATOR_PRODUCT, SUCCESS, INVENTORY_PRODUCT_SUCCESS},
            {ORCHESTRATOR_PRODUCT, FAIL, FINISH_FAIL},

            {INVENTORY_PRODUCT_SERVICE, ROLLBACK_PENDING, INVENTORY_FAIL},
            {INVENTORY_PRODUCT_SERVICE, FAIL, FINISH_FAIL},
            {INVENTORY_PRODUCT_SERVICE, SUCCESS, FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;

}
