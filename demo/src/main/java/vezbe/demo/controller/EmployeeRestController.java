package vezbe.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.EmployeeDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.Employee;
import vezbe.demo.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Employee loggedEmployee = employeeService.login(loginDto.getUsername(), loginDto.getPassword());
        if (loggedEmployee == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("employee", loggedEmployee);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Employee loggedEmployee = (Employee) session.getAttribute("employee");

        if (loggedEmployee == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(HttpSession session){
        List<Employee> employeeList = employeeService.findAll();

        Employee loggedEmployee = (Employee) session.getAttribute("employee");
        if(loggedEmployee == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedEmployee);
        }

        List<EmployeeDto> dtos = new ArrayList<>();
        for(Employee employee : employeeList){
            EmployeeDto dto = new EmployeeDto(employee);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/employees/{id}")
    public Employee getEmployee(@PathVariable(name = "id") Long id, HttpSession session){
        Employee employee = (Employee) session.getAttribute("user");
        System.out.println(employee.getFirstname());
        session.invalidate();
        return employeeService.findOne(id);
    }

    @PostMapping("/api/save-employee")
    public String saveEmployee(@RequestBody Employee employee) {
        this.employeeService.save(employee);
        return "Successfully saved an employee!";
    }
}
