package org.example;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UsersAPI {
    @BeforeMethod
    public void setup() {
        baseURI = "https://dummyjson.com";
    }
    public String getToken() {
        JSONObject credintial = new JSONObject();
        credintial.put("username", "kminchelle");
        credintial.put("password", "0lelplR");
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credintial.toJSONString())
                .when()
                .post("/auth/login");
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("token");
    }

    @Test
    public void getSpecificUserWithValidRequest() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/users/" + key);
        Assert.assertEquals(200, response.statusCode());
        System.out.println("id received from Response : " + response.jsonPath().get("id"));
    }

    @Test
    public void getSpecificUserWithInvalidRequestCase1() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .post("/auth/users/" + key);
        Assert.assertEquals(415, response.statusCode());
    }

    @Test
    public void getSpecificUserWithInvalidRequestCase2() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/users1/" + key);
        Assert.assertEquals(404, response.statusCode());
    }

    @Test
    public void getSpecificUserWithInvalidKey() throws ParseException {
        String key = "-1";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/users/" + key);
        Assert.assertEquals(404, response.statusCode());
    }

    @Test
    public void getSpecificUserWithoutToken() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + "")
                .get("/auth/users/" + key);
        Assert.assertEquals(401, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator.toString());
    }

    @Test
    public void getSpecificUserInvalideTokenCase1() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + "error")
                .get("/auth/users/" + key);
        Assert.assertEquals(401, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator.toString());
    }
    @Test
    public void getSpecificUserInvalidTokenCase2() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer1 " + getToken())
                .get("/auth/users/" + key);
        Assert.assertEquals(500, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator.toString());
    }

    @Test
    public void getAllUsers() throws ParseException {
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/users");
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        int s = jsonPathEvaluator.getInt("users.size()");
        for (int i = 0; i < s; i++) {
            String id = jsonPathEvaluator.getString("users[" + i + "].id");
            System.out.println(id);
        }
    }

}