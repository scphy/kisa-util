package net.scphy.util;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author scphy 2024/7/4
 **/
@Slf4j
public class ExceptionUtils {

    public static String getStackTrace(Throwable throwable) {
        try (
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter)
        ) {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            log.error("获取异常栈信息失败", e);
            return null;
        }
    }

}
