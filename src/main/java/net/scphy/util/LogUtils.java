package net.scphy.util;

import org.slf4j.MDC;

/**
 * @author scphy 2024/6/21
 **/
public class LogUtils {

    private static final String REQUEST_ID = "requestId";

    public static String getRequestId() {
        return MDC.get(REQUEST_ID);
    }

    public static void setRequestId() {
        setRequestId(null);
    }

    public static void setRequestId(String requestId) {
        requestId = ObjectUtils.isEmpty(requestId) ? ObjectUtils.bsonId() : requestId;
        MDC.put(REQUEST_ID, requestId);
    }

    public static void removeRequestId() {
        MDC.remove(REQUEST_ID);
    }

}
