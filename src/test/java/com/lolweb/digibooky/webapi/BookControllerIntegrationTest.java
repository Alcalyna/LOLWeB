package com.lolweb.digibooky.webapi;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    @Test
    void givenABookDtoToCreate_WhenPostANewBook_ThenCreateBookInRepository_ReturnBookDto() {
        // GIVEN
        Author author = new Author("Lulinh", "Juniel");

        CreateBookDto createBookDto = new CreateBookDto()
                .setIsbn("123456789000000")
                .setAuthor(author)
                .setTitle("LOLWeB")
                .setSummary("hahahhahahahahahaha")
                .setAvailable(true);

        // WHEN
        BookDto bookDto = RestAssured
                .given()
                .body(createBookDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(BookDto.class);

        // THEN
        Assertions.assertTrue(!bookDto.getId().toString().isBlank());
        Assertions.assertEquals("LOLWeB", bookDto.getTitle());
        Assertions.assertEquals("Lulinh", bookDto.getAuthor().getFirstName());
        Assertions.assertEquals("Juniel", bookDto.getAuthor().getLastName());
        Assertions.assertEquals("hahahhahahahahahaha", bookDto.getSummary());
        Assertions.assertEquals("123456789000000",bookDto.getIsbn());
        Assertions.assertTrue(bookDto.isAvailable());
    }
}
