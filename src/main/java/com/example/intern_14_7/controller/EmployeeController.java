package com.example.intern_14_7.controller;
import com.example.intern_14_7.dto.EmployeeDto;
import com.example.intern_14_7.maper.impl.EmployeeMapper;
import com.example.intern_14_7.model.Department;
import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import com.example.intern_14_7.repository.RoleRepository;
import com.example.intern_14_7.service.DepartmentService;
import com.example.intern_14_7.service.EmployeeService;
import com.example.intern_14_7.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final RoleService roleService;
    private final EmployeeMapper employeeMapper;
    private final RoleRepository roleRepository;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper,DepartmentService departmentService, RoleRepository roleRepository,RoleService roleService) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @GetMapping("/detail")
    public String showDetail(Pageable pageable, Model model,@RequestParam(required = false) String textSearch) {
        Page<Employee> employeePage = employeeService.findAll(textSearch,pageable);
        model.addAttribute("employeePage", employeePage);
        return "employee/index";
    }

    @GetMapping("/detaillist")
    public String showDetailList(Model model,@RequestParam(required = false) String textSearch) {
        List<Employee> employeePage = employeeService.findAll(textSearch);
        model.addAttribute("employeePage", employeePage);
        return "employee/index";
    }

    @GetMapping("/create")
    public String showAdd(Model model, Pageable pageable) {
        Page<Department> departments = departmentService.findAll(pageable);
        List<Role> roles = roleService.findAll();
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departments.getContent());
        model.addAttribute("roless", roles);
        return "employee/create";
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@ModelAttribute("employee") @Valid EmployeeDto employeeDto, BindingResult bindingResult) {
        Employee employee1 = employeeMapper.toEntity(employeeDto);
        boolean checkEmail = employeeService.existsByEmail(employee1.getEmail());
        if (checkEmail) {
            ModelAndView modelAndView = new ModelAndView("employee/create");
            modelAndView.addObject("errorMessage", "Email đã tồn tại. Vui lòng thử lại!");
            return modelAndView;
        }
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("employee/create");
            return modelAndView;
        }
        employeeService.save(employee1);
        EmployeeDto employeeDto1 = employeeMapper.toDto(employee1);
        ModelAndView modelAndView = new ModelAndView("employee/index");
        modelAndView.addObject("employeeDto", employeeDto1);
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
    public String doEdit(@PathVariable Long id, @ModelAttribute("employee") @Valid EmployeeDto employeeDto,BindingResult bindingResult) {
        Employee employee = new EmployeeMapper().toEntity(employeeDto);
        if (bindingResult.hasErrors()){
            return "employee/edit";
        }
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
}