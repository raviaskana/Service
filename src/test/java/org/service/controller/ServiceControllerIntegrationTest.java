package org.service.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.service.component.PhoneService;
import org.service.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ServiceControllerIntegrationTest {

    private MockMvc mockMvc;
    @Mock
    PhoneService phoneService;
    Result result;
    ServiceController serviceController;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        serviceController = new ServiceController();
        serviceController.phoneService = this.phoneService;
        mockMvc = MockMvcBuilders
                .standaloneSetup(serviceController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
        result = new Result();
        given(phoneService.getAlphanumericNumbers(anyString(), Optional.of(""),Optional.of(""))).willReturn(result);
    }

    @Test
    public void givenPhoneNum_whenGetAlphaNumPhoneNum_thenReturnJsonArray() throws Exception {
        when(phoneService.getAlphanumericNumbers(anyString(), Optional.of(""),Optional.of(""))).thenReturn(result);
        mockMvc.perform(get("/api/v1/phonenum/{phoneNum}/alphanum","123 456 7890")).andExpect(status().isOk());
    }
}
