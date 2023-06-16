package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class APITraining {

    @Test
    public void testGet1() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
    }

    @Test
    public void testGet2() {
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200);
    }

    @Test
    public void testGet3() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[1]", equalTo(8))
                .log().all();
    }

    @Test
    public void testGet4() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testPost1() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("\"name\"", "Raghav");
        map.put("job", "Teacher");
        JSONObject request = new JSONObject(map);
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
        given().body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201).log().all();

    }

    @Test
    public void testPost2() {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
        given().body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201).log().all();
    }

    @Test
    public void testPost3() {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
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
    public void testPut1() {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("/users")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testPatch1() {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in";
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
    public void testDelete1() {
        baseURI = "https://reqres.in";
        when()
                .delete("/api/users/2")
                .then()
                .statusCode(204).log().all();
    }


    @Test
    public void get() {
        baseURI = "https://reqres.in/api";
        given().get("/users").then().statusCode(200).log().all();
    }

    @Test
    public void post() {
        JSONObject request = new JSONObject();
        request.put("\"name\"", "Raghav");
        request.put("job", "Teacher");

        baseURI = "https://reqres.in/api";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/api/users/2")
                .then()
                .statusCode(201).log().all();
    }

    @Test
    public void getMap() throws ParseException {
        Response response = RestAssured.get("https://reqres.in/api/users/1");
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(response.asString());
        System.out.println(">>>>>>>>>>>>" + data_obj);
        JSONObject obj = (JSONObject) data_obj.get("data");
        System.out.println(obj.get("id"));
        System.out.println(obj.get("first_name"));
        System.out.println(obj.get("last_name"));
        System.out.println(obj.get("email"));
        System.out.println(obj.get("avatar"));






//        Response response = RestAssured.get("https://reqres.in/api/users/1");

//        String str = response.asString();
//        String tmp = str.substring(9,str.indexOf("support")-3);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + str);
//        responseObject obj = new Gson().fromJson(str, responseObject.class);
//        System.out.println("---" + obj);
//        System.out.println("ID: " +obj.getId());
//        System.out.println("Name: " +obj.getFirst_name());
//        System.out.println("Name: " +obj.getLast_name());
//        System.out.println("Name: " +obj.getEmail());
//        System.out.println("Name: " +obj.getAvatar());





//        System.out.println(response.getStatusCode());
//        System.out.println(response.getTime());
//        System.out.println(response.getBody().asString());
//        System.out.println(response.getStatusLine());
//        System.out.println(response.getHeader("content-type"));
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, 200);


//        Product product = ...jsonPath().getObject("data[0].products.find {it.product_code == 2}", Product.class);
//        System.out.println(product);
////Product(default_term=15, max_available_loan_amount=2000.0)


//        baseURI = "https://reqres.in/api";
//        RequestSpecification httpRequest = RestAssured.given().get("/users?page=2").te
//        responseObject obj = (responseObject) RestAssured.given().get("/users?page=2").as(responseObject.class);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + obj.getId());


//                get(uri + "/movie/" + testMovie.getId()).then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .as(Movie.class);
//        assertThat(result).isEqualTo(testMovie);

//        baseURI = "https://reqres.in/api";
//        RequestSpecification httpRequest = RestAssured.given();
//        Response response = httpRequest.get("/users");
//        ResponseBody body = response.getBody();
//        System.out.println("Response Body is: " + body.asString());
//        String value = "{first_name = naresh,last_name = kumar,gender = male}";
//        value = value.substring(1, value.length() - 1);           //remove curly brackets
//        String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
//        Map<String, String> map = new HashMap<>();
//
//        for (String pair : keyValuePairs)                        //iterate over the pairs
//        {
//            String[] entry = pair.split("=");                   //split the pairs to get key and value
//            map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
//
//        }
//        Data data = new Gson().fromJson(body.asString(), Data.class);
//        System.out.println(data);

    }
}