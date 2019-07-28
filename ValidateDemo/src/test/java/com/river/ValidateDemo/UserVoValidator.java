package com.river.ValidateDemo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-19 21:21
 **/
public class UserVoValidator {
    private static Logger logger= LoggerFactory.getLogger(UserVoValidator.class);
    private static Validator validator;
    @BeforeClass
    public static void setUpValidator(){
        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }
    @Test
    public void userIsNull(){
        UserVo userVo=new UserVo();
        userVo.setId("你最帅");
        userVo.setUserName("关注我");
        userVo.setBigDecimal(new BigDecimal(88));
        userVo.setIntValue(32432);
        userVo.setDoubleValue(22.3);
        userVo.setEmail("sdfsdfdsfs");
        userVo.setFlagFalse(true);
        userVo.setFlagTrue(false);
        userVo.setPhoneNo("2343243");
        Set<ConstraintViolation<UserVo>> constraintViolationSet=validator.validate(userVo);
        for(Iterator<ConstraintViolation<UserVo>> iterator=constraintViolationSet.iterator();iterator.hasNext();){
            ConstraintViolation<UserVo> constraintViolation=iterator.next();
            logger.info("验证结果,属性:{},结果:{}",constraintViolation.getPropertyPath(),constraintViolation.getMessage());
        }
    }
}
