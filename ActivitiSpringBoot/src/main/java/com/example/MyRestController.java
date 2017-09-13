package com.example;

/*
 * 
 * @author Surjeet Karmakar
 * 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	@Autowired
	private MyService myService;

	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public void startProcessInstanceByID(@RequestParam String processId) {
		myService.startProcess(processId);
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public void startProcessInstanceByVariables(@RequestParam String processId, @RequestBody Map<String,Object> variables) {
		myService.startProcess(processId,variables);
	}
	
	
	@RequestMapping(value = "/queryTaskAssignee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskRepresentation> queryTaskAssignee(@RequestParam String assignee) {
		List<Task> tasks = myService.queryTaskAssignee(assignee);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName(),task.getProcessDefinitionId(),task.getDescription()));
		}
		return dtos;
	}
	
	@RequestMapping(value = "/queryTaskCandidateUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskRepresentation> queryTaskCandidateUser(@RequestParam String user) {
		List<Task> tasks = myService.queryTaskCandidateUser(user);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName(),task.getProcessDefinitionId(),task.getDescription()));
		}
		return dtos;
	}
	
	@RequestMapping(value = "/queryTaskCandidateGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskRepresentation> queryTaskCandidateGroup(@RequestParam String groupId) {
		List<Task> tasks = myService.queryTaskCandidateGroup(groupId);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName(),task.getProcessDefinitionId(),task.getDescription()));
		}
		
		return dtos;
	}
	
	@RequestMapping(value = "/claimTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void claimTask(@RequestBody Map<String,String> claimDetails) {
		for(String taskId: claimDetails.keySet()){
		myService.claimTask(taskId,claimDetails.get(taskId));
		}		
	}
	
	@RequestMapping(value = "/completeTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void completeTask(@RequestParam String taskId){
		  myService.completeTask(taskId);
	}
	
	@RequestMapping(value = "/completeTask", method = RequestMethod.POST)
	public void completeTask(@RequestParam String taskId, @RequestBody Map<String,Object> variables) {
		myService.completeTask(taskId,variables);
	}
	
	@RequestMapping(value = "/listUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRepresentation> listUser(){
		List<User> users=myService.listUser();
		List<UserRepresentation> userRep= new ArrayList();
		for(User user:users){
			userRep.add(new UserRepresentation(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
		}
		
		return userRep;
	}
	
	@RequestMapping(value = "/listGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Group> listGroup(){
		return  myService.listGroup();
	}
	
	static class UserRepresentation{
		private String  id;
		private String firstName;
		private String lastName;
		private String email;
		public UserRepresentation(String id, String firstName, String lastName,
				String email) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		
		
		
	}

	static class TaskRepresentation {
		private String id;
		private String name;
		private String processDefId;
		private String description;

		public TaskRepresentation(String id, String name, String processDefId, String description) {
			this.id = id;
			this.name = name;
			this.processDefId=processDefId;
			this.description=description;
		}
		
		

		
		public String getDescription() {
			return description;
		}




		public void setDescription(String description) {
			this.description = description;
		}




		public String getProcessDefId() {
			return processDefId;
		}



		public void setProcessDefId(String processDefId) {
			this.processDefId = processDefId;
		}



		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}