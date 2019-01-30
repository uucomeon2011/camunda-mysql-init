package com.lcl.camunda.demo.controller;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
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
 * @Date: 2019/1/24 16:59
 * @Description:
 */
@RestController
public class TestController {
    private final Logger logger = getLogger(this.getClass());

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;//查询历史流程实例

    private String processInstanceId;

    @GetMapping("/process")//已经部署的流程
    public void process() {
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Sample").singleResult();
        logger.info("发现已经部署的流程: {}", processDefinition);
        logger.info("流程定义描述: {},{}", processDefinition.getName(),processDefinition.getId());
        Assert.notNull(processDefinition, "process 'Sample' should be deployed!");
    }

    @GetMapping("/start")
    public void startProcessInstance(String process){
        //process=Sample
        //process=TwitterDemoProcess
        processInstanceId = runtimeService.startProcessInstanceByKey(process).getProcessInstanceId();
        logger.info("启动流程实例: {}-{}",process, processInstanceId);

        runtimeService.createProcessInstanceQuery().active().list();
        List<ProcessInstance> pis = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(process).list();
        logger.info("启动流程实例pis: {}",process, pis);
    }

    @GetMapping("/task")
    public void completeTask(){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        logger.info("通过流程实例ID查询任务: {},{}", task.getName(),task.getId());

        Map<String, Object> variables = new HashMap<>();
        variables.put("testKey1",  "11111");
        variables.put("testKey2", "22222");
        taskService.complete(task.getId(), variables);
        logger.info("completed task: {}", task);
    }

    @GetMapping("/history")
    public boolean isProcessInstanceFinished() {
        final HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        logger.info("流程实例执行完成？{}", historicProcessInstance != null && historicProcessInstance.getEndTime() != null);
        return historicProcessInstance != null && historicProcessInstance.getEndTime() != null;
    }

}
