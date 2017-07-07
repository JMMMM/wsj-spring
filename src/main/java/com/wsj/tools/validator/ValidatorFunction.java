package com.wsj.tools.validator;


import com.wsj.sys.enums.ErrorCode;

public interface ValidatorFunction<T> {
    ErrorCode validate(T obj);
}