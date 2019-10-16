package com.river.DroolsDemo.service;

import com.river.DroolsDemo.entity.QueryParam;

public interface RuleEngineService {
    void executeAddRule(QueryParam param) ;
    void executeRemoveRule(QueryParam param) ;
}
