package com.river.batch.processor;

import com.river.batch.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @author jian
 * @date 2019/4/28
 * @description
 * CSV文件数据处理及校验
 * 只需要实现ItemProcessor接口，重写process方法，输入的参数是从ItemReader读取到的数据，返回的数据给ItemWriter
 */
@Slf4j
public class CvsItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {
        // 执行super.process()才能调用自定义的校验器
        log.info("processor start validating...");
        super.process(item);

        // 数据处理，比如将中文性别设置为M/F
        if ("男".equals(item.getGender())) {
            item.setGender("M");
        } else {
            item.setGender("F");
        }
        log.info("processor end validating...");
        return item;
    }
}