package com.wsj.tools.validator;


public interface ValidatorFunction<T> {
    boolean validate(T obj);
}