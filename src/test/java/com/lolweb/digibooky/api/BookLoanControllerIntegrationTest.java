package com.lolweb.digibooky.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BookLoanControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void bookNotInRepository() throws Exception {
        String json = "{\n" +
                "    \"isbn\": \"97807581109\"\n" +
                "}\n";

        this.mvc.perform(post("/bookloans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", "Basic Z2VycmlAbG9sd2ViLmNvbTpnZXJyaQ==")
                        .content(json))
                .andExpect(status().reason(containsString("Couldn't find the requested book in our library")));

    }
}