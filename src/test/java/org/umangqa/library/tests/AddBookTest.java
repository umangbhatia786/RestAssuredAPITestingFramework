package org.umangqa.library.tests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.umangqa.library.builders.TestDatabuilder;
import org.umangqa.library.pojo.addbook.AddBookRequest;
import org.umangqa.library.pojo.addbook.AddBookResponse;
import org.umangqa.library.specs.SpecBuilder;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AddBookTest extends BaseTest {

    public static String createdBookId;

    @Test(priority = 0)
    public void addBookTest() {
        AddBookRequest payload = TestDatabuilder.addBookRequestRandomPayload();

        AddBookResponse res = given().log().all()
                .body(payload)
                .when()
                .post("/Library/Addbook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .body(matchesJsonSchemaInClasspath("schemas/addBookResponse.json"))
                .extract().body().as(AddBookResponse.class);

        //Verify the msg field from response JSON
        Assert.assertEquals(res.getMsg(),"successfully added");

        //Save BookID so that can be used later
        createdBookId = res.getID();
    }
}
