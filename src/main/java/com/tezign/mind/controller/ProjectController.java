package com.tezign.mind.controller;


import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.tezign.mind.common.Result;
import com.tezign.mind.data.AddProject;
import com.tezign.mind.data.Project;
import com.tezign.mind.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    public Result<Project> addProject(@RequestBody AddProject addProject){

        Result<Project> result = new Result<Project>(true);


        try{

            Project project = projectService.addProject(addProject);

            result.setData(project);
        }catch (Throwable t){
            log.error("add project error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }


    @PostMapping("/addBatch")
    public Result<List<Project>> addProject(@RequestBody List<AddProject> addProjectList){

        Result<List<Project>> result = new Result<List<Project>>(true);


        try{

            List<Project> projectList = new ArrayList<>();

            for (AddProject addProject: addProjectList ) {
                Project project = projectService.addProject(addProject);
                projectList.add(project);
            }


            result.setData(projectList);
        }catch (Throwable t){
            log.error("add project error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }

    @GetMapping("/getProjectByPid/{id}")
    public Result<Project> getProject(@PathVariable("id") Long id){
        Result<Project> result = new Result<Project>(true);


        try{

            Project project = projectService.findProjectByPid(id);

            result.setData(project);
        }catch (Throwable t){
            log.error("get project error,{0}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
        }

        return result;
    }

    @DeleteMapping("/delProjectByPid/{id}")
    public Result<String> delProject(@PathVariable("id") Long id){

        Result<String> result = new Result<String>(true);

        Transaction tran = Cat.newTransaction("Project","deleteProject");

        try{

            projectService.delProjectByPid(id);
            result.setData("OK");
            tran.setStatus(Transaction.SUCCESS);
        }catch (Throwable t){
            log.error("delete project error,{}",t.getMessage());
            result.setSuccess(false);
            result.setMessage(t.getMessage());
            tran.setStatus(t);
            Cat.logError(t);
        }finally {
            tran.complete();
        }

        return result;

    }



}
