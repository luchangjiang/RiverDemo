package com.river.configuration.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-21 22:53
 **/
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //返回值为String数组类型，实际值应该是注入Bean类的全类名。
        return new String[]{"com.river.configuration.pojo.Lecturer"};
    }
}

