package com.example.core.saga;

import static com.example.core.enums.EEventProductSource.ORCHESTRATOR;
import static com.example.core.enums.EEventProductSource.*;
import static com.example.core.enums.EProductStatus.*;
import static com.example.core.enums.ETopic.*;

public final class HandlerProduct {
    private HandlerProduct(){}

    public static final Object[][] HANDLER_PRODUCT = {
            {ORCHESTRATOR, SUCCESS, INVENTORY_SUCCESS},
            {ORCHESTRATOR, FAIL, FINISH_FAIL},

            {INVENTORY_SERVICE, ROLLBACK_PENDING, INVENTORY_FAIL},
            {INVENTORY_SERVICE, FAIL, FINISH_FAIL},
            {INVENTORY_SERVICE, SUCCESS, FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;

}
