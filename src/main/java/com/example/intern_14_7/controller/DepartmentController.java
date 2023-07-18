package com.example.intern_14_7.controller;
import com.example.intern_14_7.dto.DepartmentDto;
import com.example.intern_14_7.dto.EmployeeDto;
import com.example.intern_14_7.maper.impl.DepartmentMapper;
import com.example.intern_14_7.model.Department;
import com.example.intern_14_7.model.Employee;
import com.example.intern_14_7.model.Role;
import com.example.intern_14_7.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/detail")
    public String showDetail(Pageable pageable, Model model, @RequestParam(required = false) String textSearch) {
        Page<Department> departments = departmentService.findAll(pageable);
        model.addAttribute("employee", departments);
        return "department/index";
    }

    @GetMapping("/detaillist")
    public String showDetaillist(Model model, @RequestParam(required = false) String textSearch) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employee", departments);
        return "department/index";
    }
    @GetMapping("/index")
    public static ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/create")
    public String showAdd(Model model, Pageable pageable) {
        model.addAttribute("department", new Department());
        return "department/create";
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("department/create");
            return modelAndView;
        }
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("department/index");
        modelAndView.addObject("department", department);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model,Pageable pageable){
        Department department = departmentService.findById(id);
        if (department!=null) {
            model.addAttribute("department", department);
            return "department/edit";
        } else {
            return "redirect:/department/detail";
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Long id, @ModelAttribute("department") @Valid Department department,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "department/edit";
        }
        department.setId(id);
        departmentService.save(department);
        return "redirect:/department/detail";
    }
}
