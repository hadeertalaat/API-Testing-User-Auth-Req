package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class APIRequests {
    @BeforeMethod
    public void setup() {
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSpecificMovie() throws ParseException {
        String id = "2";
        Response response = given().get("/users/" + id);
        Assert.assertEquals(200, response.statusCode());
        JSONParser parse = new JSONParser();
        JSONObject obj = (JSONObject) parse.parse(response.asString());
        JSONObject data_obj = (JSONObject) obj.get("data");
        Assert.assertEquals(id,String.valueOf(data_obj.get("id")));
    }

    @Test
    public void postAMovie() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201).log().all();
    }

    @Test
    public void putAMovie() throws ParseException {
        String id = "2";
        JSONObject request = new JSONObject();
        request.put("\"name\"", "morpheus");
        request.put("job", "zion resident");
        given()
                .body(request.toJSONString())
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void patchAMovie() throws ParseException {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("/api/users/2")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void deleteAMovie() throws ParseException {
        when()
                .delete("/api/users/2")
                .then()
                .statusCode(204).log().all();
    }

}