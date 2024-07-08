package com.claimapplicationportal.admin.controller;

import com.claimapplicationportal.admin.exception.AdminException;
import com.claimapplicationportal.admin.model.*;
import com.claimapplicationportal.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders ="*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    public String login(@RequestBody Login loginDetails) throws AdminException {
        return adminService.login(loginDetails);
    }

    @GetMapping(value = "/authorize")
    public boolean authorizeTheRequest(
            @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws AdminException {
        return adminService.validateToken(requestTokenHeader);

    }

    @PostMapping(value = "/registerAdmin")
    public Admin registerAdmin(@RequestBody Admin adminDetails) throws AdminException {
        return adminService.registerAdmin(adminDetails);
    }

    @GetMapping(value = "/allUsers")
    public List<ServiceDetails> getAllUsers() {
        return adminService.getAllUsers();
    }


    @PostMapping(value = "/saveService")
    public ServiceDetails addService(@RequestBody ServiceDetails serviceDetails) throws AdminException {
        return adminService.addService(serviceDetails);

    }

    @PutMapping(value = "/updateService")
    public ServiceDetails updateService(@RequestBody ServiceDetails serviceDetails) throws AdminException {
        return adminService.updateService(serviceDetails);
    }

    @PutMapping(value = "/updateTemplate")
    public Template updateTemplate(@RequestBody Template template) throws AdminException {
        return adminService.updateTemplate(template);
    }


    @GetMapping(value = "/getAllServices")
    public List<ServiceDetails> getAllServices() {
        return adminService.getAllServices();
    }


    @DeleteMapping(value = "/deleteServiceById/{serviceId}")
    public String deleteService(@PathVariable int serviceId) throws AdminException {
        return adminService.deleteService(serviceId);
    }

    @GetMapping(value = "/getServiceById/{serviceId}")
    public ServiceDetails getServiceById(@PathVariable int serviceId) throws AdminException {
        return adminService.getServiceById(serviceId);
    }


    @PostMapping(value = "/addTemplate")
    public Template addTemplate(@RequestBody Template template) throws AdminException {
        return adminService.addTemplate(template);
    }

    @GetMapping(value = "/getTemplates")
    public List<Template> getTemplates() throws AdminException {
        return adminService.getTemplates();
    }

    @GetMapping(value = "/getTemplateById/{templateId}")
    public Template getTemplateById(@PathVariable int templateId) {
        return adminService.getTemplateById(templateId);
    }

    @PostMapping(value="/mailsender/{toEmail}")
    public String sendOtpEmail(@PathVariable String toEmail){
        return adminService.sendOtpEmail(toEmail);
    }



    @DeleteMapping(value = "/deleteTemplateById/{templateId}")
    public String deleteTemplateById(@PathVariable int templateId) throws AdminException {
        return adminService.deleteTemplateById(templateId);
    }


    @PostMapping(value = "/forgotPassword")
    public String forgotPassword(@RequestBody Admin admin) throws AdminException {
        return adminService.forgotPassword(admin.getEmailId(), admin.getPassword());
    }

    @GetMapping(value = "/getHospitals")
    public List<Hospital> getHospitals() {
        return adminService.getHospitals();
    }

}

