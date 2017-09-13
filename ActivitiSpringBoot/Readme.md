http://localhost:8090/queryTaskAssignee?assignee=kermit

http://localhost:8090/queryTaskCandidateGroup?groupId=management

http://localhost:8090/queryTaskCandidateUser?user=gonzo

GET:
http://localhost:8090/process?processId=oneTaskProcess

POST:
http://localhost:8090/process?processId=vacationRequestRevised

{
  "numberOfDays": 12,
  "startDate": "13-09-17",
  "vacationMotivation": "Need some rest....",
 "employeeName" : "fozzie"
}

POST:
http://localhost:8090/claimTask
{
  "27519": "gonzo"
}

POST:
http://localhost:8090/completeTask?taskId=27519

{
  "vacationApproved":"true",
  "managerMotivation":"request is approved"
}
