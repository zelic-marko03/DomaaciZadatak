package vezbe.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vezbe.demo.dto.EmployeeDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.dto.ProjectDto;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.service.EmployeeService;
import vezbe.demo.service.ProjectService;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDto>> getEmployees(HttpSession session){
        Employee loggedEmployee = (Employee) session.getAttribute("employee");
        List<ProjectDto> dtos=new ArrayList<>();

        if(loggedEmployee == null) {
            List<Project> projectList=projectService.findAll();
            for(Project project : projectList){
                ProjectDto dto=new ProjectDto(project);
                dtos.add(dto);
            }
        } else {
            Set<Project> projectList=projectService.findProjects(loggedEmployee.getId());
            for(Project project : projectList){
                ProjectDto dto=new ProjectDto(project);
                dtos.add(dto);
            }
        }

        return ResponseEntity.ok(dtos);
    }





}