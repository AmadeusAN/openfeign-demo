package com.anhun.openfeign_demo.Error;

import com.anhun.openfeign_demo.entity.Param;
import com.anhun.openfeign_demo.service.OpenFeignService;
import org.springframework.stereotype.Component;

@Component
public class OpenFeignFallback implements OpenFeignService {
    private final String ERROR = "远程调用错误";

    @Override
    public String hello() {
        return ERROR;
    }

    @Override
    public String finduser(String username) {
        return ERROR;
    }

    @Override
    public String testArray() {
        return ERROR;
    }

    @Override
    public String sendRequestParam(String param1, int param2) {
        return ERROR;
    }

    @Override
    public String sendParamByRequestBody(String name) {
        return ERROR;
    }

    @Override
    public String sendRequestParam(int id) {
        return ERROR;
    }

    @Override
    public String sendEntity(Param param) {
        return null;
    }
}
