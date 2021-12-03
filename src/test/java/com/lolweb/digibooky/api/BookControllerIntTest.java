package com.lolweb.digibooky.api;

import com.lolweb.digibooky.service.BookService;
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
class BookControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookService bookService;

    @Test
    void addABookWithoutTitle_ThrowsError() throws Exception {
        String json = "{\n" +
                "    \"isbn\": \"9781119584704\",\n" +
                "    \"title\": \"\",\n" +
                "    \"author\": {\n" +
                "        \"firstName\": \"Jeanne\",\n" +
                "        \"lastName\": \"D'arc\"\n" +
                "    },\n" +
                "    \"summary\": \"Study Guide: Exam 1Z0-816 were published before Oracle announced major changes to its OCP certification program and the release of the new Developer 1Z0-819 exam.\",\n" +
                "    \"available\": true\n" +
                "}";

        this.mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu")
                .content(json))
                .andExpect(status().reason(containsString("The title should be filled!")));

//        MvcResult result = mvc.perform(get("/books?title=group by")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
////                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)));
////                .andExpect(jsonPath("$[0].getTitle()"), is());
//
//        String content = result.getResponse().getContentAsString();
//        System.out.println(content);

    }
}