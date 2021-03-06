package com.tweetapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.dto.AuthenticationRequest;
import com.tweetapp.entities.UserModel;
import com.tweetapp.services.UserOperationsService;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Kalyan Kommulapati
 * @project tweet_app_api_service
 * @since 09/07/2022 - 11:41 PM
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAuthControllerTest {

    @Mock
    private UserOperationsService userModelService;

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserAuthController userAuthController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userAuthController).build();
    }

    @Test
    public void testSubscribeClient() throws Exception {
        final UserModel userModel = UserModel.builder()
                .username("ikalyan183@gmail.com")
                .firstName("kalyan")
                .lastName("says")
                .email("ikalyan183@gmail.com")
                .contactNum("12345678910")
                .password("password")
                .build();


        Mockito.doReturn(userModel).when(userModelService).createUser(userModel);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/tweets/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(userModel))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void testAuthenticateClientWithException() throws Exception {

        final AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("Test");
        authenticationRequest.setPassword("TestPassword");

        Mockito.doAnswer(invocation -> {
                    throw new Exception("Username provided is invalid");
                })
                .when(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken("Test", "TestPassword"));

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/tweets/login")
                .content(convertToJson(authenticationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andReturn();
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.is4xxClientError());
    }


    private static String convertToJson(Object ob) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(ob);
    }
}
