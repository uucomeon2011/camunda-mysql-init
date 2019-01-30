package com.lcl.camunda.demo.controller;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Auther: liuchunli3
 * @Date: 2019/1/25 14:04
 * @Description:
 */
@RestController
public class ApprovalController {
    private final Logger logger = getLogger(this.getClass());
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;//查询历史流程实例

    @GetMapping("/apl/start")//启动审批流程
    public void start(Integer productType){
        Map<String,Object> vars = new HashMap<>();
        Order order = new Order();
        order.setId(4L);
        order.setMoney(5001.0);
        order.setProductType(productType);
        //productType==2需要审核 其它情况不需要
        vars.put("order",order);
        vars.put("userId","1001");
        vars.put("productType",productType);

        runtimeService.startProcessInstanceByKey("approval",vars).getProcessInstanceId();
    }

    @GetMapping("/apl/tasks")//查询审批任务列表
    public Map<String,Object> tasks(){

        List<Task> approvalTasks = taskService.createTaskQuery().processDefinitionKey("approval").list();
        //查询任务
        Map<String,Object> response = new HashMap<>();

        response.put("data",approvalTasks);
        response.put("code",200);
        response.put("message","查询任务成功");
        return response;
    }

}
