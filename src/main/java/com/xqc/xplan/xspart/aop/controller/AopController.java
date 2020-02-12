package com.xqc.xplan.xspart.aop.controller;

import com.xqc.xplan.xspart.aop.bean.Data;
import com.xqc.xplan.xspart.aop.service.AopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    private final AopService aopService;

    public AopController(AopService aopService) {
        this.aopService = aopService;
    }

    @GetMapping("/aop")
    public String aop()
    {
        Data data = new Data();
        aopService.doAop(data);

        return data.toString();
    }


}
