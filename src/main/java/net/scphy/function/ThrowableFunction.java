package net.scphy.function;

/**
 * @author scphy 2024/6/29
 **/
@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Exception;

}
