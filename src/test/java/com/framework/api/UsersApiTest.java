package com.framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class UsersApiTest {
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void createUser_returns201AndEchoesPayload() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users");

        assertEquals(response.statusCode(), 201);
        response.then().body("name", org.hamcrest.Matchers.equalTo("morpheus"));
        response.then().body("id", notNullValue());
    }

    @Test
    public void getUser_returnsExpectedFields() {
        Response response = given().when().get("/users/2");
        assertEquals(response.statusCode(), 200);
        assertTrue(response.jsonPath().getString("data.email").contains("@"));
    }

    @Test
    public void deleteUser_returns204() {
        Response response = given().when().delete("/users/2");
        assertEquals(response.statusCode(), 204);
    }
}
