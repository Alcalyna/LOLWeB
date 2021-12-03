package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.dtos.CreateUserDto;
import com.lolweb.digibooky.service.dtos.UserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    @Test
    void registerAMemberDto_WhenPostANewMember_ThenCreateMemberInUserRepository_ReturnUserDto() {
        //GIVEN

        Address address = Address.AddressBuilder.addressBuilder().withCity("Brussels").build();

        CreateUserDto createUserDto = new CreateUserDto()
                .setAddress(address)
                .setEmailAddress(new EmailAddress("newmember", "lolweb.com"))
                .setFirstName("lola")
                .setLastName("lalo")
                .setInss("987451478523")
                .setPassword("lola")
                .setRole(User.Role.MEMBER);

        // WHEN
        UserDto userDto = RestAssured
                .given()
                .header("authorization", "Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu")
                .body(createUserDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDto.class);

        // THEN
        Assertions.assertEquals(address, userDto.getAddress());
        Assertions.assertEquals(new EmailAddress("newmember", "lolweb.com"), userDto.getEmailAddress());
        Assertions.assertEquals("lola", userDto.getFirstName());
        Assertions.assertEquals("lalo", userDto.getLastName());
        Assertions.assertEquals("987451478523", userDto.getInss());
        Assertions.assertEquals(User.Role.MEMBER, userDto.getRole());
    }


}