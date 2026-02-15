package com.microservice.kafka_producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka_producer.spring_test.UserTestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ConfigurationPropertiesScan(value = "classpath:application.yaml")
@SpringBootTest(classes = KafkaProducerApplication.class)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkGetName() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/getname"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                        ).andExpect(MockMvcResultMatchers.content().string("actual test message"))
                .andReturn();
        mvcResult.getRequest().getCookies();
    }

    @Nested
    class checkGroupTest {

        ObjectMapper objectMapper = new ObjectMapper();
        String reqJson;

        @Mock
        private UserTestDto userTestDto;

        @BeforeEach
        public void setup() throws Exception {
            userTestDto = new UserTestDto(12, "Test");
            reqJson = objectMapper.writeValueAsString(userTestDto);
        }

        @Test
        public void regristerUser() throws Exception {
            System.out.println("Application name :"+applicationName);
            mockMvc.perform(MockMvcRequestBuilders.post("/adduser")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(reqJson))
                    .andExpect(MockMvcResultMatchers
                            .jsonPath("$.status").value("200"));
        }

    }

}
