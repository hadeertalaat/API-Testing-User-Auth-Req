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

public class APIRequests_products {
    @BeforeMethod
    public void setup() {
        baseURI = "https://dummyjson.com";
    }
    public String getToken() throws ParseException {
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
    public void getSpecificProduct() throws ParseException {
        String key = "2";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/products/" + key);
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        String title = jsonPathEvaluator.get("products");
        System.out.println("title received from Response : " + title);
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

    @Test
    public void getAllProducts() throws ParseException {
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/products");
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        int s = jsonPathEvaluator.getInt("products.size()");
        for (int i = 0; i < s; i++) {
            String id = jsonPathEvaluator.getString("products[" + i + "].id");
            System.out.println(id);
            String title = jsonPathEvaluator.getString("products[" + i + "].title");
            System.out.println(title);
            String description = jsonPathEvaluator.getString("products[" + i + "].description");
            System.out.println(description);
            String price = jsonPathEvaluator.getString("products[" + i + "].price");
            System.out.println(price);
            String discountPercentage = jsonPathEvaluator.getString("products[" + i + "].discountPercentage");
            System.out.println(discountPercentage);
            String rating = jsonPathEvaluator.getString("products[" + i + "].rating");
            System.out.println(rating);
            String stock = jsonPathEvaluator.getString("products[" + i + "].stock");
            System.out.println(stock);
            String brand = jsonPathEvaluator.getString("products[" + i + "].brand");
            System.out.println(brand);
            String category = jsonPathEvaluator.getString("products[" + i + "].category");
            System.out.println(category);
            String thumbnail = jsonPathEvaluator.getString("products[" + i + "].thumbnail");
            System.out.println(thumbnail);
            String images = jsonPathEvaluator.getString("products[" + i + "].images");
            System.out.println(images);
        }
    }

    @Test
    public void getAllTheCategoriseTheProducts() throws ParseException {
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/products/categories");
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        int size = jsonPathEvaluator.getInt("size()");
        for (int i = 0; i < size; i++) {
            String category = jsonPathEvaluator.getString("[" + i + "]");
            System.out.println(category);
        }
    }

    @Test
    public void getProductsWithSearchingForSpecificType() throws ParseException {
        String key = "Laptop";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/products/search?q=" + key);
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        int s = jsonPathEvaluator.getInt("products.size()");
        for (int i = 0; i < s; i++) {
            String id = jsonPathEvaluator.getString("products[" + i + "].id");
            System.out.println(id);
            String title = jsonPathEvaluator.getString("products[" + i + "].title");
            System.out.println(title);
            String description = jsonPathEvaluator.getString("products[" + i + "].description");
            System.out.println(description);
            String price = jsonPathEvaluator.getString("products[" + i + "].price");
            System.out.println(price);
            String discountPercentage = jsonPathEvaluator.getString("products[" + i + "].discountPercentage");
            System.out.println(discountPercentage);
            String rating = jsonPathEvaluator.getString("products[" + i + "].rating");
            System.out.println(rating);
            String stock = jsonPathEvaluator.getString("products[" + i + "].stock");
            System.out.println(stock);
            String brand = jsonPathEvaluator.getString("products[" + i + "].brand");
            System.out.println(brand);
            String category = jsonPathEvaluator.getString("products[" + i + "].category");
            System.out.println(category);
            String thumbnail = jsonPathEvaluator.getString("products[" + i + "].thumbnail");
            System.out.println(thumbnail);
            String images = jsonPathEvaluator.getString("products[" + i + "].images");
            System.out.println(images);
        }
    }

    @Test
    public void getProductsWithSpecificType() throws ParseException {
        String key = "smartphones";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .get("/auth/products/category/" + key);
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        int s = jsonPathEvaluator.getInt("products.size()");
        for (int i = 0; i < s; i++) {
            String id = jsonPathEvaluator.getString("products[" + i + "].id");
            System.out.println(id);
            String title = jsonPathEvaluator.getString("products[" + i + "].title");
            System.out.println(title);
            String description = jsonPathEvaluator.getString("products[" + i + "].description");
            System.out.println(description);
            String price = jsonPathEvaluator.getString("products[" + i + "].price");
            System.out.println(price);
            String discountPercentage = jsonPathEvaluator.getString("products[" + i + "].discountPercentage");
            System.out.println(discountPercentage);
            String rating = jsonPathEvaluator.getString("products[" + i + "].rating");
            System.out.println(rating);
            String stock = jsonPathEvaluator.getString("products[" + i + "].stock");
            System.out.println(stock);
            String brand = jsonPathEvaluator.getString("products[" + i + "].brand");
            System.out.println(brand);
            String category = jsonPathEvaluator.getString("products[" + i + "].category");
            System.out.println(category);
            String thumbnail = jsonPathEvaluator.getString("products[" + i + "].thumbnail");
            System.out.println(thumbnail);
            String images = jsonPathEvaluator.getString("products[" + i + "].images");
            System.out.println(images);
        }
    }

    @Test
    public void postAProduct() throws ParseException {
        JSONObject product = new JSONObject();
        product.put("id", "200");
        product.put("title", "perfume Oil");
        product.put("description", "the Mega Discount, Impression of A...");
        product.put("price", "13");
        product.put("discountPercentage", "8.4");
        product.put("rating", "4.26");
        product.put("stock", "65");
        product.put("brand", "Impression of Acqua Di Gio");
        product.put("category", "fragrances");
        product.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumbnail.jpg");
        product.put("images", "Teacher");
        product.put("job", null);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .body(product.toJSONString())
                .post("/auth/products/add");
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String id = jsonPathEvaluator.getString("id");
        System.out.println(id);
        String title = jsonPathEvaluator.getString("title");
        System.out.println(title);
        String description = jsonPathEvaluator.getString("description");
        System.out.println(description);
        String price = jsonPathEvaluator.getString("price");
        System.out.println(price);
        String discountPercentage = jsonPathEvaluator.getString("discountPercentage");
        System.out.println(discountPercentage);
        String rating = jsonPathEvaluator.getString("rating");
        System.out.println(rating);
        String stock = jsonPathEvaluator.getString("stock");
        System.out.println(stock);
        String brand = jsonPathEvaluator.getString("brand");
        System.out.println(brand);
        String category = jsonPathEvaluator.getString("category");
        System.out.println(category);
        String thumbnail = jsonPathEvaluator.getString("thumbnail");
        System.out.println(thumbnail);
        String images = jsonPathEvaluator.getString("images");
        System.out.println(images);
    }

    @Test
    public void putAProduct() throws ParseException {
        String key = "2";
        JSONObject product = new JSONObject();
        product.put("title", "perfume Oil");
        product.put("description", "the Mega Discount, Impression of A...");
        product.put("price", "13");
        product.put("discountPercentage", "8.4");
        product.put("rating", "4.26");
        product.put("stock", "65");
        product.put("brand", "Impression of Acqua Di Gio");
        product.put("category", "fragrances");
        product.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumbnail.jpg");
        product.put("images", "Teacher");
        product.put("job", null);
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .body(product.toJSONString())
                .put("/auth/products/" + key);
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String id = jsonPathEvaluator.getString("id");
        System.out.println(id);
        String title = jsonPathEvaluator.getString("title");
        System.out.println(title);
        String description = jsonPathEvaluator.getString("description");
        System.out.println(description);
        String price = jsonPathEvaluator.getString("price");
        System.out.println(price);
        String discountPercentage = jsonPathEvaluator.getString("discountPercentage");
        System.out.println(discountPercentage);
        String rating = jsonPathEvaluator.getString("rating");
        System.out.println(rating);
        String stock = jsonPathEvaluator.getString("stock");
        System.out.println(stock);
        String brand = jsonPathEvaluator.getString("brand");
        System.out.println(brand);
        String category = jsonPathEvaluator.getString("category");
        System.out.println(category);
        String thumbnail = jsonPathEvaluator.getString("thumbnail");
        System.out.println(thumbnail);
        String images = jsonPathEvaluator.getString("images");
        System.out.println(images);
    }


    @Test
    public void patchAProduct() throws ParseException {
        String key = "2";
        JSONObject product = new JSONObject();
        product.put("title", "perfume Oil");
        product.put("description", "the Mega Discount, Impression of A...");
        product.put("price", "13");
        product.put("discountPercentage", "8.4");
        product.put("rating", "4.26");
        product.put("stock", "65");
        product.put("brand", "Impression of Acqua Di Gio");
        product.put("category", "fragrances");
        product.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumbnail.jpg");
        product.put("images", "Teacher");
        product.put("job", null);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .body(product.toJSONString())
                .patch("/auth/products/" + key);
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String id = jsonPathEvaluator.getString("id");
        System.out.println(id);
        String title = jsonPathEvaluator.getString("title");
        System.out.println(title);
        String description = jsonPathEvaluator.getString("description");
        System.out.println(description);
        String price = jsonPathEvaluator.getString("price");
        System.out.println(price);
        String discountPercentage = jsonPathEvaluator.getString("discountPercentage");
        System.out.println(discountPercentage);
        String rating = jsonPathEvaluator.getString("rating");
        System.out.println(rating);
        String stock = jsonPathEvaluator.getString("stock");
        System.out.println(stock);
        String brand = jsonPathEvaluator.getString("brand");
        System.out.println(brand);
        String category = jsonPathEvaluator.getString("category");
        System.out.println(category);
        String thumbnail = jsonPathEvaluator.getString("thumbnail");
        System.out.println(thumbnail);
        String images = jsonPathEvaluator.getString("images");
        System.out.println(images);
    }
    @Test
    public void deleteAProductById() throws ParseException {
        String key = "1";
        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .delete("/products/" + key);
        Assert.assertEquals(200, response.statusCode());
    }

}