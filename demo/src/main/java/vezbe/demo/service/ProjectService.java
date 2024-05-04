package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Employee;
import vezbe.demo.model.Project;
import vezbe.demo.repository.EmployeeRepository;
import vezbe.demo.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ProjectService {

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public ProjectRepository projectRepository;

    public Project findOne(Long id){
        Optional<Project> foundProject = projectRepository.findById(id);
        if (foundProject.isPresent())
            return foundProject.get();

        return null;
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Set<Project> findProjects(Long id){
        Optional<Employee> foundEmployee=employeeRepository.findById(id);
        if(foundEmployee.isPresent()){
            return foundEmployee.get().getProjects();
        }
        else{
            return null;
        }
    }

}