package com.river.DroolsDemo.controller;

<<<<<<< HEAD
import com.river.DroolsDemo.entity.QueryParam;
import com.river.DroolsDemo.entity.RuleResult;
import com.river.DroolsDemo.service.RuleEngineService;
import org.kie.api.runtime.KieSession;
=======
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
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
<<<<<<< HEAD
=======
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c

@RestController
@RequestMapping("/rule")
public class RuleController {

<<<<<<< HEAD
    @Resource
    private KieSession kieSession;
    @Resource
    private RuleEngineService ruleEngineService ;

    @RequestMapping("/param")
    public void param (){
        QueryParam queryParam1 = new QueryParam() ;
        queryParam1.setParamId("1");
        queryParam1.setParamSign("+");
        QueryParam queryParam2 = new QueryParam() ;
        queryParam2.setParamId("2");
        queryParam2.setParamSign("-");
        // 入参
        kieSession.insert(queryParam1) ;
        kieSession.insert(queryParam2) ;
        kieSession.insert(this.ruleEngineService) ;

        // 返参
        RuleResult resultParam = new RuleResult() ;
        kieSession.insert(resultParam) ;
=======
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
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
        kieSession.fireAllRules() ;
    }
}
