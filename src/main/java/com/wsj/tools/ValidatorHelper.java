package com.wsj.tools;

import com.wsj.sys.enums.ErrorCode;
import com.wsj.tools.validator.ValidatorFunction;

/**
 * Created by Jimmy on 2017/7/7.
 */
public class ValidatorHelper {
    public static <T> ErrorCode validator(T obj, ValidatorFunction validatorFunction) {
        return validatorFunction.validate(obj);
    }
}


