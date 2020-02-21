package com.river.DroolsDemo.controller;

import com.river.DroolsDemo.entity.ParamDTO;
import com.river.DroolsDemo.entity.ParamInfo;
import com.river.DroolsDemo.service.ParamInfoService;
import com.river.DroolsDemo.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class RuleController {

    /*@Resource
    private StatelessKieSession statelessKieSession;*/

    @Resource
    private KieSession kieSession;

    /*@Autowired
    private ParamInfoService paramInfoService;*/

    @PostMapping("/param")
    public void param (@RequestBody ParamDTO paramDTO){
        Map inputMap = new HashMap<String, Object>();
        ParamInfo paramInfo = new ParamInfo();
        paramInfo.setOperate(paramDTO.getOperate());
        paramInfo.setParamA(paramDTO.getParamA());
        paramInfo.setParamB(paramDTO.getParamB());
        paramInfo.setCreateTime(new Date());
//        statelessKieSession.execute(paramInfo);

        inputMap.put("paramInfo", paramInfo);
        // 入参

        ParamInfoService paramInfoService = (ParamInfoService)SpringContextUtil.getBean("paramInfoService");
        inputMap.put("paramInfoService", paramInfoService);
        kieSession.insert(inputMap) ;
//        kieSession.insert(paramInfo) ;
        kieSession.fireAllRules() ;
    }
}
