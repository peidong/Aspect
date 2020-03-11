package com.xqc.xplan.xspart.excel2sql.controller;

import com.xqc.xplan.xspart.aop.bean.Data;
import com.xqc.xplan.xspart.aop.service.AopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Excel2SqlController {

    private final AopService aopService;

    public Excel2SqlController(AopService aopService) {
        this.aopService = aopService;
    }

    @GetMapping("/excel2sql")
    public String excel2sql()
    {
        Data data = new Data();
        aopService.doAop(data);

        return data.toString();
    }


}
