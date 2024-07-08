package com.claimapplicationportal.admin.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.claimapplicationportal.admin.model.Admin;
import com.claimapplicationportal.admin.model.Login;
import com.claimapplicationportal.admin.model.ServiceDetails;
import com.claimapplicationportal.admin.model.Template;
import com.claimapplicationportal.admin.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    /**
     * Method under test: {@link AdminController#login(Login)}
     */
    @Test
    void testLogin() throws Exception {
        when(adminService.login(Mockito.<Login>any())).thenReturn("Login");

        Login login = new Login();
        login.setEmailId("42");
        login.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(login);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login"));
    }

    /**
     * Method under test: {@link AdminController#registerAdmin(Admin)}
     */
    @Test
    void testRegisterAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        when(adminService.registerAdmin(Mockito.<Admin>any())).thenReturn(admin);

        Admin admin2 = new Admin();
        admin2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin2.setEmailId("42");
        admin2.setPassword("iloveyou");
        admin2.setUpdatedBy("2020-03-01");
        admin2.setUpdatedOn("2020-03-01");
        admin2.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(admin2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registerAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"emailId\":\"42\",\"password\":\"iloveyou\",\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdOn\":\"Jan"
                                        + " 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020-03-01\",\"updatedOn\":\"2020-03-01\"}"));
    }

    /**
     * Method under test: {@link AdminController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(adminService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allUsers");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(adminService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allUsers");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#addService(ServiceDetails)}
     */
    @Test
    void testAddService() throws Exception {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        when(adminService.addService(Mockito.<ServiceDetails>any())).thenReturn(serviceDetails);

        ServiceDetails serviceDetails2 = new ServiceDetails();
        serviceDetails2.setCategories(new ArrayList<>());
        serviceDetails2.setClaimAmount(1L);
        serviceDetails2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setServiceId(1);
        serviceDetails2.setServiceName("Service Name");
        serviceDetails2.setUpdatedBy("2020-03-01");
        serviceDetails2.setUpdatedOn("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(serviceDetails2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveService")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"serviceId\":1,\"serviceName\":\"Service Name\",\"claimAmount\":1,\"createdBy\":\"Jan 1, 2020 8:00am"
                                        + " GMT+0100\",\"createdOn\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020-03-01\",\"updatedOn\":\"2020-03-01"
                                        + "\",\"categories\":[]}"));
    }

    /**
     * Method under test: {@link AdminController#updateService(ServiceDetails)}
     */
    @Test
    void testUpdateService() throws Exception {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        when(adminService.updateService(Mockito.<ServiceDetails>any())).thenReturn(serviceDetails);

        ServiceDetails serviceDetails2 = new ServiceDetails();
        serviceDetails2.setCategories(new ArrayList<>());
        serviceDetails2.setClaimAmount(1L);
        serviceDetails2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails2.setServiceId(1);
        serviceDetails2.setServiceName("Service Name");
        serviceDetails2.setUpdatedBy("2020-03-01");
        serviceDetails2.setUpdatedOn("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(serviceDetails2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateService")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"serviceId\":1,\"serviceName\":\"Service Name\",\"claimAmount\":1,\"createdBy\":\"Jan 1, 2020 8:00am"
                                        + " GMT+0100\",\"createdOn\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020-03-01\",\"updatedOn\":\"2020-03-01"
                                        + "\",\"categories\":[]}"));
    }

    /**
     * Method under test: {@link AdminController#updateTemplate(Template)}
     */
    @Test
    void testUpdateTemplate() throws Exception {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        when(adminService.updateTemplate(Mockito.<Template>any())).thenReturn(template);

        Template template2 = new Template();
        template2.setAmountAllocated(10L);
        template2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template2.setServices(new ArrayList<>());
        template2.setTemplateId(1);
        template2.setTemplateName("Template Name");
        template2.setUpdatedBy("2020-03-01");
        template2.setUpdatedOn("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(template2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateTemplate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"templateId\":1,\"templateName\":\"Template Name\",\"services\":[],\"amountAllocated\":10,"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdOn\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020"
                                        + "-03-01\",\"updatedOn\":\"2020-03-01\"}"));
    }

    /**
     * Method under test: {@link AdminController#getAllServices()}
     */
    @Test
    void testGetAllServices() throws Exception {
        when(adminService.getAllServices()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllServices");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#deleteService(int)}
     */
    @Test
    void testDeleteService() throws Exception {
        when(adminService.deleteService(anyInt())).thenReturn("Delete Service");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteServiceById/{serviceId}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Service"));
    }

    /**
     * Method under test: {@link AdminController#getServiceById(int)}
     */
    @Test
    void testGetServiceById() throws Exception {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setCategories(new ArrayList<>());
        serviceDetails.setClaimAmount(1L);
        serviceDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        serviceDetails.setServiceId(1);
        serviceDetails.setServiceName("Service Name");
        serviceDetails.setUpdatedBy("2020-03-01");
        serviceDetails.setUpdatedOn("2020-03-01");
        when(adminService.getServiceById(anyInt())).thenReturn(serviceDetails);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getServiceById/{serviceId}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"serviceId\":1,\"serviceName\":\"Service Name\",\"claimAmount\":1,\"createdBy\":\"Jan 1, 2020 8:00am"
                                        + " GMT+0100\",\"createdOn\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020-03-01\",\"updatedOn\":\"2020-03-01"
                                        + "\",\"categories\":[]}"));
    }

    /**
     * Method under test: {@link AdminController#addTemplate(Template)}
     */
    @Test
    void testAddTemplate() throws Exception {
        Template template = new Template();
        template.setAmountAllocated(10L);
        template.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template.setServices(new ArrayList<>());
        template.setTemplateId(1);
        template.setTemplateName("Template Name");
        template.setUpdatedBy("2020-03-01");
        template.setUpdatedOn("2020-03-01");
        when(adminService.addTemplate(Mockito.<Template>any())).thenReturn(template);

        Template template2 = new Template();
        template2.setAmountAllocated(10L);
        template2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        template2.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        template2.setServices(new ArrayList<>());
        template2.setTemplateId(1);
        template2.setTemplateName("Template Name");
        template2.setUpdatedBy("2020-03-01");
        template2.setUpdatedOn("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(template2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addTemplate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"templateId\":1,\"templateName\":\"Template Name\",\"services\":[],\"amountAllocated\":10,"
                                        + "\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdOn\":\"Jan 1, 2020 8:00am GMT+0100\",\"updatedBy\":\"2020"
                                        + "-03-01\",\"updatedOn\":\"2020-03-01\"}"));
    }

    /**
     * Method under test: {@link AdminController#getTemplates()}
     */
    @Test
    void testGetTemplates() throws Exception {
        when(adminService.getTemplates()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getTemplates");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#deleteTemplateById(int)}
     */
    @Test
    void testDeleteTemplateById() throws Exception {
        when(adminService.deleteTemplateById(anyInt())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteTemplateById/{templateId}",
                1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }

    /**
     * Method under test: {@link AdminController#forgotPassword(Admin)}
     */
    @Test
    void testForgotPassword() throws Exception {
        when(adminService.forgotPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn("iloveyou");

        Admin admin = new Admin();
        admin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        admin.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        admin.setEmailId("42");
        admin.setPassword("iloveyou");
        admin.setUpdatedBy("2020-03-01");
        admin.setUpdatedOn("2020-03-01");
        admin.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(admin);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/forgotPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("iloveyou"));
    }

    /**
     * Method under test: {@link AdminController#getHospitals()}
     */
    @Test
    void testGetHospitals() throws Exception {
        when(adminService.getHospitals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getHospitals");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getHospitals()}
     */
    @Test
    void testGetHospitals2() throws Exception {
        when(adminService.getHospitals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getHospitals");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#authorizeTheRequest(String)}
     */
    @Test
    void testAuthorizeTheRequest() throws Exception {
        when(adminService.validateToken(Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authorize")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link AdminController#authorizeTheRequest(String)}
     */
    @Test
    void testAuthorizeTheRequest2() throws Exception {
        when(adminService.validateToken(Mockito.<String>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authorize")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    @Test
    void login() {
    }

    @Test
    void authorizeTheRequest() {
    }

    @Test
    void registerAdmin() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void addService() {
    }

    @Test
    void updateService() {
    }

    @Test
    void updateTemplate() {
    }

    @Test
    void getAllServices() {
    }

    @Test
    void getAllTemplates() {
    }

    @Test
    void deleteService() {
    }

    @Test
    void getServiceById() {
    }

    @Test
    void addTemplate() {
    }

    @Test
    void getTemplates() {
    }

//    @Test
//    void getAllTemplates(){
//
//    }

    @Test
    void deleteTemplateById() {
    }

    @Test
    void forgotPassword() {
    }

    @Test
    void getHospitals() {
    }
}

