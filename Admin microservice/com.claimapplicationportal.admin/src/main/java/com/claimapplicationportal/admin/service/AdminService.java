package com.claimapplicationportal.admin.service;

import com.claimapplicationportal.admin.exception.AdminException;
import com.claimapplicationportal.admin.model.*;
import com.claimapplicationportal.admin.repository.AdminRepo;
import com.claimapplicationportal.admin.repository.HospitalRepo;
import com.claimapplicationportal.admin.repository.ServiceRepo;
import com.claimapplicationportal.admin.repository.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private TemplateRepo templateRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private JavaMailSender javaMailSender;



    public String login(Login loginDetails) throws AdminException {
        Optional<Admin> admin = adminRepo.findById(loginDetails.getEmailId());
        if (admin.isPresent() && admin.get().getPassword().equals(loginDetails.getPassword())) {
            return "Bearer  " + jwtUtil.generateToken(loginDetails);
        } else throw new AdminException("Invalid Credentials");

    }

    public Boolean validateToken(String jwtTokenHeader) throws AdminException {
        String jwtToken = null;
        if (jwtTokenHeader != null && jwtTokenHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenHeader.substring(7);
            try {

                return !jwtUtil.extractUsername(jwtToken).isEmpty();
            } catch (Exception e) {
                return false;
            }
        } else throw new AdminException("Require Bearer Token");
    }

    public Admin registerAdmin(Admin adminDetails) throws AdminException {
        if (!adminRepo.findById(adminDetails.getEmailId()).isPresent()) {
            return adminRepo.save(adminDetails);
        } else throw new AdminException("Primary key Violation");

    }

    public List<ServiceDetails> getAllUsers() {
        return serviceRepo.findAll();
    }


    public ServiceDetails addService(ServiceDetails serviceDetails) throws AdminException {
        if (!serviceRepo.findById(serviceDetails.getServiceId()).isPresent()) {
            return serviceRepo.save(serviceDetails);
        } else throw new AdminException("Primary key Violation");
    }

    public ServiceDetails updateService(ServiceDetails serviceDetails) throws AdminException {
        Optional<ServiceDetails> oldDetails = serviceRepo.findById(serviceDetails.getServiceId());
        if (oldDetails.isPresent()) {
            oldDetails.get().setServiceName(serviceDetails.getServiceName());
            oldDetails.get().setClaimAmount(serviceDetails.getClaimAmount());
            oldDetails.get().setCategories(serviceDetails.getCategories());

            return serviceRepo.save(oldDetails.get());
        } else throw new AdminException("Service is not available");
    }


    public List<ServiceDetails> getAllServices() throws IllegalArgumentException {
          return serviceRepo.findAll();
    }



    public Template addTemplate(Template template) throws AdminException {
        if (!templateRepo.findById(template.getTemplateId()).isPresent()) {
            return templateRepo.save(template);
        } else throw new AdminException("Primary key Violation");

    }

    public List<Template> getTemplates() throws AdminException {
        if (!templateRepo.findAll().isEmpty()) {
            return templateRepo.findAll();
        } else throw new AdminException("No Templates are present");
    }


    public String deleteTemplateById(int templateId) throws AdminException {
        try {
            templateRepo.deleteById(templateId);
            return "Successfully Template deleted";
        } catch (Exception e) {
            throw new AdminException(e.toString());
        }
    }


    public String forgotPassword(String userName, String newPassword) throws AdminException {
        Optional<Admin> admin = adminRepo.findById(userName);
        if (admin.isPresent()) {
            admin.get().setPassword(newPassword);
            adminRepo.save(admin.get());
            return "password successfully updated";
        } else throw new AdminException("Invalid username");
    }

    public String deleteService(int serviceId) throws AdminException {
        Optional<ServiceDetails> serviceDetails = serviceRepo.findById(serviceId);
        if (serviceDetails.isPresent()) {
            serviceRepo.deleteById(serviceId);
            return "Successful deleted";
        } else throw new AdminException("service is not available");
    }

    public ServiceDetails getServiceById(int serviceId) throws AdminException {
        Optional<ServiceDetails> serviceDetails = serviceRepo.findById(serviceId);
        if (serviceDetails.isPresent()) {
            return serviceDetails.get();
        } else throw new AdminException("service is not available");

    }


    public Template updateTemplate(Template template) throws AdminException {
        Optional<Template> oldDetails=templateRepo.findById(template.getTemplateId());
        if(oldDetails.isPresent()){
            oldDetails.get().setTemplateName(template.getTemplateName());
            oldDetails.get().setServices(template.getServices());
            oldDetails.get().setAmountAllocated(template.getAmountAllocated());


            return templateRepo.save(oldDetails.get());
        }else throw new AdminException("Template is not available");

    }

    public List<Hospital> getHospitals() {
        return hospitalRepo.findAll();
    }



    public String sendOtpEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Reset Password");
        message.setText("Click Here to Change your password\n"+"http://localhost:4200/forgotpassword");
        javaMailSender.send(message);
        return "vasavimunny26@gmail.com";
    }

    public Template getTemplateById(int templateId) {
        return templateRepo.getReferenceById(templateId);
    }


}

