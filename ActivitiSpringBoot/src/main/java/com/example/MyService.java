package com.example;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private IdentityService identityService;

	@Transactional
	public void startProcess(String processId) {
		runtimeService.startProcessInstanceByKey(processId);
	}
	
	@Transactional
	public void startProcess(String processId, Map<String,Object> variables){
		runtimeService.startProcessInstanceByKey(processId,variables);
	}

	@Transactional
	public List<Task> queryTaskAssignee(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}
	
	@Transactional
	public List<Task> queryTaskCandidateUser(String user){
		return taskService.createTaskQuery()
			.taskCandidateUser(user).list();
	}
	
	@Transactional
	public List<Task> queryTaskCandidateGroup(String groupId){
	 return	taskService.createTaskQuery()
		.taskCandidateGroup(groupId).list();
	}
	
	@Transactional
	public void claimTask(String taskId, String user){
		taskService.claim(taskId, user);
	}
	
	@Transactional
	public List<User> listUser(){
		return identityService.createUserQuery().list();
	}
	
	@Transactional
	public List<Group> listGroup(){
		return identityService.createGroupQuery().list();
	}
	
	@Transactional
	public void completeTask(String taskId){
		taskService.complete(taskId);
	}
	
	@Transactional
	public void completeTask(String taskId,Map<String,Object> variables){
		taskService.complete(taskId,variables);
	}
	

}