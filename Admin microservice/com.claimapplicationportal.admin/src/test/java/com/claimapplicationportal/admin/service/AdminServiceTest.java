package com.claimapplicationportal.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.claimapplicationportal.admin.exception.AdminException;
import com.claimapplicationportal.admin.model.Admin;
import com.claimapplicationportal.admin.model.Hospital;
import com.claimapplicationportal.admin.model.Login;
import com.claimapplicationportal.admin.model.ServiceDetails;
import com.claimapplicationportal.admin.model.Template;
import com.claimapplicationportal.admin.repository.AdminRepo;
import com.claimapplicationportal.admin.repository.HospitalRepo;
import com.claimapplicationportal.admin.repository.ServiceRepo;
import com.claimapplicationportal.admin.repository.TemplateRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @MockBean
    private HospitalRepo hospitalRepo;

    @MockBean
    private AdminRepo adminRepo;

    @Autowired
    private AdminService adminService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private ServiceRepo serviceRepo;

    @MockBean
    private TemplateRepo templateRepo;

    /**
     * Method under test: {@link AdminService#login(Login)}
     */
    @Test
    void testLogin() throws AdminException {
        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtUtil.generateToken(Mockito.<Login>any())).thenReturn("ABC123");

        Login loginDetails = new Login();
        loginDetails.setEmailId("42");
        loginDetails.setPassword("iloveyou");
        assertEquals("Bearer  ABC123", adminService.login(loginDetails));
        verify(adminRepo).findById(Mockito.<String>any());
        verify(jwtUtil).generateToken(Mockito.<Login>any());
    }

    /**
     * Method under test: {@link AdminService#login(Login)}
     */
    @Test
    void testLogin2() throws AdminException {
        Admin admin = mock(Admin.class);
        when(admin.getPassword()).thenReturn("foo");
        doNothing().when(admin).setCreatedBy(Mockito.<String>any());
        doNothing().when(admin).setCreatedOn(Mockito.<String>any());
        doNothing().when(admin).setEmailId(Mockito.<String>any());
        doNothing().when(admin).setPassword(Mockito.<String>any());
        doNothing().when(admin).setUpdatedBy(Mockito.<String>any());
        doNothing().when(admin).setUpdatedOn(Mockito.<String>any());
        doNothing().when(admin).setUserId(anyInt());
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(ofResult);

        Login loginDetails = new Login();
        loginDetails.setEmailId("42");
        loginDetails.setPassword("iloveyou");
        assertThrows(AdminException.class, () -> adminService.login(loginDetails));
        verify(adminRepo).findById(Mockito.<String>any());
        verify(admin).getPassword();
        verify(admin).setCreatedBy(Mockito.<String>any());
        verify(admin).setCreatedOn(Mockito.<String>any());
        verify(admin).setEmailId(Mockito.<String>any());
        verify(admin).setPassword(Mockito.<String>any());
        verify(admin).setUpdatedBy(Mockito.<String>any());
        verify(admin).setUpdatedOn(Mockito.<String>any());
        verify(admin).setUserId(anyInt());
    }

    /**
     * Method under test: {@link AdminService#login(Login)}
     */
    @Test
    void testLogin3() throws AdminException {
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        Login loginDetails = new Login();
        loginDetails.setEmailId("42");
        loginDetails.setPassword("iloveyou");
        assertThrows(AdminException.class, () -> adminService.login(loginDetails));
        verify(adminRepo).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#validateToken(String)}
     */
    @Test
    void testValidateToken() throws AdminException {
        assertThrows(AdminException.class, () -> adminService.validateToken("ABC123"));
        assertThrows(AdminException.class, () -> adminService.validateToken(null));
    }

    /**
     * Method under test: {@link AdminService#validateToken(String)}
     */
    @Test
    void testValidateToken2() throws AdminException {
        when(jwtUtil.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        assertTrue(adminService.validateToken("Bearer "));
        verify(jwtUtil).extractUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#validateToken(String)}
     */
    @Test
    void testValidateToken3() throws AdminException {
        when(jwtUtil.extractUsername(Mockito.<String>any())).thenReturn(null);
        assertFalse(adminService.validateToken("Bearer "));
        verify(jwtUtil).extractUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#validateToken(String)}
     */
    @Test
    void testValidateToken4() throws AdminException {
        when(jwtUtil.extractUsername(Mockito.<String>any())).thenReturn("");
        assertFalse(adminService.validateToken("Bearer "));
        verify(jwtUtil).extractUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#registerAdmin(Admin)}
     */
    @Test
    void testRegisterAdmin() throws AdminException {
        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(ofResult);

        Admin adminDetails = new Admin();
        adminDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        adminDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        adminDetails.setEmailId("42");
        adminDetails.setPassword("iloveyou");
        adminDetails.setUpdatedBy("2020-03-01");
        adminDetails.setUpdatedOn("2020-03-01");
        adminDetails.setUserId(1);
        assertThrows(AdminException.class, () -> adminService.registerAdmin(adminDetails));
        verify(adminRepo).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#registerAdmin(Admin)}
     */
    @Test
    void testRegisterAdmin2() throws AdminException {
        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        when(adminRepo.save(Mockito.<Admin>any())).thenReturn(admin);
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        Admin adminDetails = new Admin();
        adminDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        adminDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        adminDetails.setEmailId("42");
        adminDetails.setPassword("iloveyou");
        adminDetails.setUpdatedBy("2020-03-01");
        adminDetails.setUpdatedOn("2020-03-01");
        adminDetails.setUserId(1);
        assertSame(admin, adminService.registerAdmin(adminDetails));
        verify(adminRepo).save(Mockito.<Admin>any());
        verify(adminRepo).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<ServiceDetails> serviceDetailsList = new ArrayList<>();
        when(serviceRepo.findAll()).thenReturn(serviceDetailsList);
        List<ServiceDetails> actualAllUsers = adminService.getAllUsers();
        assertSame(serviceDetailsList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(serviceRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#addService(ServiceDetails)}
     */
    @Test
    void testAddService() throws AdminException {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        Optional<ServiceDetails> ofResult = Optional.of(serviceDetails);
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        ServiceDetails serviceDetails2 = new ServiceDetails();
        serviceDetails2.setCategories(new ArrayList<>());
        serviceDetails2.setClaimAmount(1L);
        serviceDetails2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setServiceId(1);
        serviceDetails2.setServiceName("Service Name");
        serviceDetails2.setUpdatedBy("2020-03-01");
        serviceDetails2.setUpdatedOn("2020-03-01");
        assertThrows(AdminException.class, () -> adminService.addService(serviceDetails2));
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#addService(ServiceDetails)}
     */
    @Test
    void testAddService2() throws AdminException {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        when(serviceRepo.save(Mockito.<ServiceDetails>any())).thenReturn(serviceDetails);
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        ServiceDetails serviceDetails2 = new ServiceDetails();
        serviceDetails2.setCategories(new ArrayList<>());
        serviceDetails2.setClaimAmount(1L);
        serviceDetails2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setServiceId(1);
        serviceDetails2.setServiceName("Service Name");
        serviceDetails2.setUpdatedBy("2020-03-01");
        serviceDetails2.setUpdatedOn("2020-03-01");
        assertSame(serviceDetails, adminService.addService(serviceDetails2));
        verify(serviceRepo).save(Mockito.<ServiceDetails>any());
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#updateService(ServiceDetails)}
     */
    @Test
    void testUpdateService() throws AdminException {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        Optional<ServiceDetails> ofResult = Optional.of(serviceDetails);

        ServiceDetails serviceDetails2 = new ServiceDetails();
        serviceDetails2.setCategories(new ArrayList<>());
        serviceDetails2.setClaimAmount(1L);
        serviceDetails2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setServiceId(1);
        serviceDetails2.setServiceName("Service Name");
        serviceDetails2.setUpdatedBy("2020-03-01");
        serviceDetails2.setUpdatedOn("2020-03-01");
        when(serviceRepo.save(Mockito.<ServiceDetails>any())).thenReturn(serviceDetails2);
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        ServiceDetails serviceDetails3 = new ServiceDetails();
        serviceDetails3.setCategories(new ArrayList<>());
        serviceDetails3.setClaimAmount(1L);
        serviceDetails3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails3.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails3.setServiceId(1);
        serviceDetails3.setServiceName("Service Name");
        serviceDetails3.setUpdatedBy("2020-03-01");
        serviceDetails3.setUpdatedOn("2020-03-01");
        assertSame(serviceDetails2, adminService.updateService(serviceDetails3));
        verify(serviceRepo).save(Mockito.<ServiceDetails>any());
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#updateService(ServiceDetails)}
     */
    @Test
    void testUpdateService2() throws AdminException {
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        assertThrows(AdminException.class, () -> adminService.updateService(serviceDetails));
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#getAllServices()}
     */
//    @Test
//    void testGetAllServices() throws AdminException {
//        when(serviceRepo.findAll()).thenReturn(new ArrayList<>());
//        assertThrows(AdminException.class, () -> adminService.getAllServices());
//        verify(serviceRepo).findAll();
//    }

    /**
     * Method under test: {@link AdminService#getAllServices()}
     */
    @Test
    void testGetAllServices2() throws Exception {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("There is no services available");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");

        ArrayList<ServiceDetails> serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(serviceDetails);
        when(serviceRepo.findAll()).thenReturn(serviceDetailsList);
        List<ServiceDetails> actualAllServices = adminService.getAllServices();
        assertSame(serviceDetailsList, actualAllServices);
        assertEquals(1, actualAllServices.size());
        verify(serviceRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#getAllServices()}
     */
    @Test
    void testGetAllServices3() throws Exception {
        ArrayList<ServiceDetails> serviceDetailsList = new ArrayList<>();
        when(serviceRepo.findAll()).thenReturn(serviceDetailsList);
        List<ServiceDetails> actualAllServices = adminService.getAllServices();
        assertSame(serviceDetailsList, actualAllServices);
        assertTrue(actualAllServices.isEmpty());
        verify(serviceRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#addTemplate(Template)}
     */
    @Test
    void testAddTemplate() throws AdminException {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        Optional<Template> ofResult = Optional.of(template);
        when(templateRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Template template2 = new Template();
        template2.setAmountAllocated(10L);
        template2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template2.setServices(new ArrayList<>());
        template2.setTemplateId(1);
        template2.setTemplateName("Template Name");
        template2.setUpdatedBy("2020-03-01");
        template2.setUpdatedOn("2020-03-01");
        assertThrows(AdminException.class, () -> adminService.addTemplate(template2));
        verify(templateRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#addTemplate(Template)}
     */
    @Test
    void testAddTemplate2() throws AdminException {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        when(templateRepo.save(Mockito.<Template>any())).thenReturn(template);
        when(templateRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        Template template2 = new Template();
        template2.setAmountAllocated(10L);
        template2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template2.setServices(new ArrayList<>());
        template2.setTemplateId(1);
        template2.setTemplateName("Template Name");
        template2.setUpdatedBy("2020-03-01");
        template2.setUpdatedOn("2020-03-01");
        assertSame(template, adminService.addTemplate(template2));
        verify(templateRepo).save(Mockito.<Template>any());
        verify(templateRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#getTemplates()}
     */
    @Test
    void testGetTemplates() throws AdminException {
        when(templateRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(AdminException.class, () -> adminService.getTemplates());
        verify(templateRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#getTemplates()}
     */
    @Test
    void testGetTemplates2() throws AdminException {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("No Templates are present");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");

        ArrayList<Template> templateList = new ArrayList<>();
        templateList.add(template);
        when(templateRepo.findAll()).thenReturn(templateList);
        List<Template> actualTemplates = adminService.getTemplates();
        assertSame(templateList, actualTemplates);
        assertEquals(1, actualTemplates.size());
        verify(templateRepo, atLeast(1)).findAll();
    }

    /**
     * Method under test: {@link AdminService#deleteTemplateById(int)}
     */
    @Test
    void testDeleteTemplateById() throws AdminException {
        doNothing().when(templateRepo).deleteById(Mockito.<Integer>any());
        assertEquals("Successfully Template deleted", adminService.deleteTemplateById(1));
        verify(templateRepo).deleteById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#forgotPassword(String, String)}
     */
    @Test
    void testForgotPassword() throws AdminException {
        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        Optional<Admin> ofResult = Optional.of(admin);

        Admin admin2 = new Admin();
        admin2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin2.setEmailId("42");
        admin2.setPassword("iloveyou");
        admin2.setUpdatedBy("2020-03-01");
        admin2.setUpdatedOn("2020-03-01");
        admin2.setUserId(1);
        when(adminRepo.save(Mockito.<Admin>any())).thenReturn(admin2);
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals("password successfully updated", adminService.forgotPassword("janedoe", "iloveyou"));
        verify(adminRepo).save(Mockito.<Admin>any());
        verify(adminRepo).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#forgotPassword(String, String)}
     */
    @Test
    void testForgotPassword2() throws AdminException {
        when(adminRepo.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(AdminException.class, () -> adminService.forgotPassword("janedoe", "iloveyou"));
        verify(adminRepo).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#deleteService(int)}
     */
    @Test
    void testDeleteService() throws AdminException {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        Optional<ServiceDetails> ofResult = Optional.of(serviceDetails);
        doNothing().when(serviceRepo).deleteById(Mockito.<Integer>any());
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertEquals("Successful deleted", adminService.deleteService(1));
        verify(serviceRepo).findById(Mockito.<Integer>any());
        verify(serviceRepo).deleteById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#deleteService(int)}
     */
    @Test
    void testDeleteService2() throws AdminException {
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(AdminException.class, () -> adminService.deleteService(1));
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#getServiceById(int)}
     */
    @Test
    void testGetServiceById() throws AdminException {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        Optional<ServiceDetails> ofResult = Optional.of(serviceDetails);
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertSame(serviceDetails, adminService.getServiceById(1));
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#getServiceById(int)}
     */
    @Test
    void testGetServiceById2() throws AdminException {
        when(serviceRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(AdminException.class, () -> adminService.getServiceById(1));
        verify(serviceRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#updateTemplate(Template)}
     */
    @Test
    void testUpdateTemplate() throws AdminException {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        Optional<Template> ofResult = Optional.of(template);

        Template template2 = new Template();
        template2.setAmountAllocated(10L);
        template2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template2.setServices(new ArrayList<>());
        template2.setTemplateId(1);
        template2.setTemplateName("Template Name");
        template2.setUpdatedBy("2020-03-01");
        template2.setUpdatedOn("2020-03-01");
        when(templateRepo.save(Mockito.<Template>any())).thenReturn(template2);
        when(templateRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Template template3 = new Template();
        template3.setAmountAllocated(10L);
        template3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template3.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template3.setServices(new ArrayList<>());
        template3.setTemplateId(1);
        template3.setTemplateName("Template Name");
        template3.setUpdatedBy("2020-03-01");
        template3.setUpdatedOn("2020-03-01");
        assertSame(template2, adminService.updateTemplate(template3));
        verify(templateRepo).save(Mockito.<Template>any());
        verify(templateRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#updateTemplate(Template)}
     */
    @Test
    void testUpdateTemplate2() throws AdminException {
        when(templateRepo.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        assertThrows(AdminException.class, () -> adminService.updateTemplate(template));
        verify(templateRepo).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminService#getHospitals()}
     */
    @Test
    void testGetHospitals() {
        ArrayList<Hospital> hospitalList = new ArrayList<>();
        when(hospitalRepo.findAll()).thenReturn(hospitalList);
        List<Hospital> actualHospitals = adminService.getHospitals();
        assertSame(hospitalList, actualHospitals);
        assertTrue(actualHospitals.isEmpty());
        verify(hospitalRepo).findAll();
    }
}

