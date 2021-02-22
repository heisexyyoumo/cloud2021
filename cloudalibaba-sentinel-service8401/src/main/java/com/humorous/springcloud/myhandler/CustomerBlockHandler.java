package com.humorous.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.humorous.springcloud.common.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(false, "444", "按照客户自定义,global---1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(false, "444", "按照客户自定义,global---2");
    }
}
