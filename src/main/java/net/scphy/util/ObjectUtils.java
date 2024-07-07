package net.scphy.util;

import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author scphy 2024/1/24
 **/
public class ObjectUtils {

    private static final String EMPTY_STRING = "";

    public static String toString(Object source) {
        return isEmpty(source) ? EMPTY_STRING : String.valueOf(source);
    }

    public static String toString(Object source, String defaultValue) {
        return ifEmpty(toString(source), defaultValue);
    }

    public static Integer toInteger(String source) {
        return isEmpty(source) ? null : Integer.valueOf(source);
    }

    public static Integer toInteger(String source, Integer defaultValue) {
        return ifEmpty(toInteger(source), defaultValue);
    }

    public static <T>T ifNull(T source, T defaultValue) {
        return source == null ? defaultValue : source;
    }

    public static <T>T ifEmpty(T source, T defaultValue) {
        return isEmpty(source) ? defaultValue : source;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object source) {
        if (source == null) {
            return true;
        }
        return switch (source) {
            case Optional optional -> optional.isEmpty();
            case CharSequence charSequence -> charSequence.isEmpty();
            case Collection collection -> collection.isEmpty();
            case Map map -> map.isEmpty();
            case Object[] array -> array.length == 0;
            default -> false;
        };
    }

    public static boolean isNotEmpty(Object source) {
        return !isEmpty(source);
    }

    public static String uuid(boolean replace) {
        String uuid = UUID.randomUUID().toString();
        return replace ? uuid.replace("-", "") : uuid;
    }

    public static String uuid() {
        return uuid(true);
    }

    public static String bsonId() {
        return ObjectId.get().toString();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T min(T... element) {
        if (element == null || element.length < 1) {
            return null;
        }
        return Arrays.stream(element).filter(ObjectUtils::isNotEmpty).min(Comparable::compareTo).orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T max(T... element) {
        if (element == null || element.length < 1) {
            return null;
        }
        return Arrays.stream(element).filter(ObjectUtils::isNotEmpty).max(Comparable::compareTo).orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFirstMatching(Function<T, Boolean> condition, Supplier<T>... suppliers) {
        T t = null;
        for (Supplier<T> supplier : suppliers) {
            t = supplier.get();
            if (condition.apply(t)) {
                break;
            }
        }
        return t;
    }

}
