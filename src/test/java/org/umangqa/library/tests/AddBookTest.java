package org.umangqa.library.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.umangqa.library.builders.TestDatabuilder;
import org.umangqa.library.pojo.addbook.AddBookRequest;
import org.umangqa.library.pojo.addbook.AddBookResponse;
import org.umangqa.library.specs.SpecBuilder;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Library API Testing")
@Feature("Add Book")
public class AddBookTest extends BaseTest {

    public static String createdBookId;

    @Story("Add new book to the library")
    @Description("Verifies that a new book can be successfully added and validates response contract")
    @Test(priority = 0)
    public void addBookTest() {
        step("Generate request payload for AddBook API");
        AddBookRequest payload = TestDatabuilder.addBookRequestRandomPayload();

        step("Call AddBook API and extract response");
        AddBookResponse res = given().log().all()
                .body(payload)
                .when()
                .post("/Library/Addbook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .body(matchesJsonSchemaInClasspath("schemas/addBookResponse.json"))
                .extract().body().as(AddBookResponse.class);

        //Verify the msg field from response JSON
        step("Validate AddBook response fields");
        Assert.assertEquals(res.getMsg(),"successfully added");

        //Save BookID so that can be used later
        createdBookId = res.getID();
    }
}
