package com.example.intern_14_7.controller;
import com.example.intern_14_7.dto.EmployeeDto;
import com.example.intern_14_7.maper.impl.EmployeeMapper;
import com.example.intern_14_7.model.Department;
import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import com.example.intern_14_7.repository.RoleRepository;
import com.example.intern_14_7.service.DepartmentService;
import com.example.intern_14_7.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    private final RoleRepository roleRepository;

    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper,DepartmentService departmentService, RoleRepository roleRepository) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/detail")
    public String showDetail(Pageable pageable, Model model,@RequestParam(required = false) String textSearch) {
        Page<Employee> employee = employeeService.findAll(textSearch,pageable);
        model.addAttribute("employee", employee);
        return "employee/index";
    }
    @GetMapping("/index")
    public static ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index");
        return modelAndView;
    }
    @GetMapping("/employeemanager")
    public static ModelAndView employeemanager(){
        ModelAndView modelAndView=new ModelAndView("employee/index");
        return modelAndView;
    }

    @GetMapping("/create")
    public String showAdd(Model model, Pageable pageable) {
        model.addAttribute("employee", new Employee());
        Page<Department> departments = departmentService.findAll(pageable);
        model.addAttribute("departments", departments.getContent());
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "employee/create";
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@ModelAttribute("employee") Employee employee) {
        boolean checkEmail = employeeService.existsByEmail(employee.getEmail());
        if (checkEmail) {
            ModelAndView modelAndView = new ModelAndView("employee/create");
            modelAndView.addObject("errorMessage", "Email đã tồn tại. Vui lòng thử lại!");
            return modelAndView;
        }
        employeeService.save(employee);
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        ModelAndView modelAndView = new ModelAndView("employee/index");
        modelAndView.addObject("employeeDto", employeeDto);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model,Pageable pageable){
        Employee employee = employeeService.findById(id);
        if (employee!=null) {
            model.addAttribute("employee", employee);
            Page<Department> departments = departmentService.findAll(pageable);
            model.addAttribute("departments", departments.getContent());
            return "employee/edit";
        } else {
            return "redirect:/employee/detail";
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        employeeService.save(employee);
        return "redirect:/employee/detail";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable Long id) {
        if (id == null) {
            ModelAndView modelAndView = new ModelAndView("employee/index");
            modelAndView.addObject("errorMessage", "Nhập sai id");
            return modelAndView;
        } else {
            employeeService.deleteById(id);
            ModelAndView modelAndView = new ModelAndView("employee/index");
            modelAndView.addObject("message", "Xóa thành công");
            return modelAndView;
        }
    }

    @GetMapping("/search")
    public String searchEmployees(@RequestParam(required = false) String textSearch, Pageable pageable, Model model) {
        Page<Employee> employees = employeeService.findAll(textSearch, pageable);
        model.addAttribute("employee", employees);
        return "employee/index";
    }
}