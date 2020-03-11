package com.xqc.xplan.xspart.excel2sql.service;

import com.xqc.xplan.xspart.aop.annotation.BuryingPoint;
import com.xqc.xplan.xspart.aop.bean.Data;
import org.springframework.stereotype.Service;

@Service
public class Excel2SqlService {

    @BuryingPoint
    public void doAop(Data data)
    {
        System.out.println(data);
        System.out.println(this.getClass().getName()+" 开始调用");
//        System.out.println(1/0);
        System.out.println(this.getClass().getName()+" 调用结束");
        data.setValue("Service End Set");
    }
}
