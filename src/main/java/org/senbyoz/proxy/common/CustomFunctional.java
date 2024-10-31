package org.senbyoz.proxy.common;

public class CustomFunctional {
    @FunctionalInterface
    public interface TriFunc<A, B, C, R> {
        R apply(A a, B b, C c);
    }
}
