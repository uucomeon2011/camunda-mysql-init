package com.lcl.camunda.demo.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: liuchunli3
 * @Date: 2019/1/25 13:50
 * @Description:
 */
@Component
public class RecordDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("记录执行代理: {}", execution);
        Map<String, Object> variables = execution.getVariables();
        String money = (String)variables.get("productType");
        logger.info("money is {}", money);
    }
}
