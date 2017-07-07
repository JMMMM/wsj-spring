package com.wsj.tools;

/**
 * Created by Jimmy on 2017/7/7.
 */
public class ValidatorHelper {
    public static <T> boolean validator(T obj, ValidatorFunction validatorFunction) {
        return validatorFunction.validate(obj);
    }
}


interface ValidatorFunction {
    <T> boolean validate(T obj);
}