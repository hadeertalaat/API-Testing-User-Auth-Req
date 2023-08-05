package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestingUserAuthenticationRequest {
    @BeforeMethod
    public void setup() {
        baseURI = "https://dummyjson.com";
    }
    @Test
    public void getUserByIdWithTheCorrectCredential() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(200, response.statusCode());

        String token = response.jsonPath().get("token");
        System.out.println(token);

        String key = "1";
        response = given()
                .header("Authorization", "Bearer " + token)
                .get("/auth/users/" + key);
        Assert.assertEquals(200, response.statusCode());
        System.out.println("id received from Response : " + response.jsonPath().toString());
        System.out.println("id received from Response : " + response.jsonPath().get("id"));
        Integer id = response.jsonPath().get("id");
        System.out.println("id received from Response : " + id);
        Assert.assertTrue(String.valueOf(id).matches("[0-9]+"));
    }
    @Test
    public void getUserByIncorrectIdWithTheCorrectCredential() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(200, response.statusCode());

        String token = response.jsonPath().get("token");
        System.out.println(token);

        String key = "-1";
        response = given()
                .header("Authorization", "Bearer " + token)
                .get("/auth/users/" + key);
        Assert.assertEquals(404, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("id"));
    }
    @Test
    public void getUserByIdWithIncorrectUrl() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(200, response.statusCode());

        String token = response.jsonPath().get("token");
        System.out.println(token);

        String key = "1";
        response = given()
                .header("Authorization", "Bearer " + token)
                .get("/auth/users1/" + key);
        Assert.assertEquals(404, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("id"));
    }
    @Test
    public void getUserByIdWithIncorrectRequest() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(200, response.statusCode());

        String token = response.jsonPath().get("token");

        String key = "1";
        response = given()
                .header("Authorization", "Bearer " + token)
                .post("/auth/users/" + key);
        Assert.assertEquals(415, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("id"));
    }
    @Test
    public void getUserByIdWithIncorrectToken() {
        String token = "1";
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("/auth/users/" + key);
        Assert.assertEquals(401, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
    @Test
    public void validateWithIncorrectEndpoint() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/loginn");

        Assert.assertEquals(403, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("id"));
    }
    @Test
    public void validateWithIncorrectRequest() {
        String username = "kminchelle";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .put("/auth/login");

        Assert.assertEquals(403, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
    @Test
    public void validateWithIncorrectUsername() {
        String incorrectUsername = "kminchelle1";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", incorrectUsername);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(400, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
    @Test
    public void validateWithEmptyUsername() {
        String incorrectUsername = "";
        String password = "0lelplR";

        JSONObject credential = new JSONObject();
        credential.put("username", incorrectUsername);
        credential.put("password", password);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(400, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
    @Test
    public void validateWithIncorrectPassword() {
        String username = "kminchelle";
        String incorrectPassword = "0lelplR1";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", incorrectPassword);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(400, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
    @Test
    public void validateWithEmptyPassword() {
        String username = "kminchelle";
        String incorrectPassword = "";

        JSONObject credential = new JSONObject();
        credential.put("username", username);
        credential.put("password", incorrectPassword);

        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credential.toJSONString())
                .when()
                .post("/auth/login");

        Assert.assertEquals(400, response.statusCode());
        Assert.assertNull((String) response.jsonPath().get("token"));
    }
}