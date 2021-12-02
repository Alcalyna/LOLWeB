//package com.lolweb.digibooky.api;
//
//import com.lolweb.digibooky.service.BookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.UUID;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
////@WebMvcTest(BookController.class)
//@SpringBootTest
//class BookControllerIntTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private
//
//    @MockBean
//    private BookService bookService;
//
//    @Test
//    void bookByTitle() throws Exception {
////        RequestBuilder request = MockMvcRequestBuilders.get("/books?title=group by");
//        UUID id = UUID.fromString("a6501d07-a051-437d-b074-08bf8be9f247");
//        given(bookService.getBookByTitle("group by").get(0)).willReturn(bookService.getBookById(id));
//
//        MvcResult result = mvc.perform(get("/books?title=group by")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
////                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)));
////                .andExpect(jsonPath("$[0].getTitle()"), is());
//
//        String content = result.getResponse().getContentAsString();
//        System.out.println(content);
//
//    }
//}