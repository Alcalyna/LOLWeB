package com.lolweb.digibooky.webapi;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import io.restassured.RestAssured;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerIntegrationTest {

    public static final String ANSI_RED = "\u001B[31m";

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
//        System.out.println(ANSI_RED + "This is red : " + bookDto.getId() + ANSI_RED);
        assertThat(!bookDto.getId().toString().isBlank());
        assertThat(bookDto.getTitle().equals("LOLWeB"));
        assertThat(bookDto.getAuthor().getFirstName().equals("Lulinh"));
        assertThat(bookDto.getAuthor().getLastName().equals("Juniel"));
        assertThat(bookDto.getIsbn().equals("123456789000000"));
        assertThat(bookDto.getIsAvailable());
    }
}
