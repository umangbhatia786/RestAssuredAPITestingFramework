package org.umangqa.library.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.umangqa.library.pojo.getbook.byAuthor.GetBookByAuthorResponse;
import org.umangqa.library.pojo.getbook.byID.GetBookByIdResponse;
import org.umangqa.library.specs.SpecBuilder;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetBookTest extends BaseTest {

    @Test(dependsOnMethods = "org.umangqa.library.tests.AddBookTest.addBookTest", priority = 1)
    public void getBookByIdTest() {
        GetBookByIdResponse[] response = given().log().all()
                .queryParam("ID", AddBookTest.createdBookId)
                .when()
                .get("/Library/GetBook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .body(matchesJsonSchemaInClasspath("schemas/getBookByIdResponse.json"))
                .extract().body().as(GetBookByIdResponse[].class);

        Assert.assertTrue(response.length > 0, "No book returned for ID: " + AddBookTest.createdBookId);
        GetBookByIdResponse book = response[0];
        Assert.assertEquals(book.getIsbn() + book.getAisle(), AddBookTest.createdBookId);
    }

    @Test(priority = 2)
    public void getBookByAuthorTest() {

        GetBookByAuthorResponse[] response = given().log().all()
                .queryParam("AuthorName", "John Doe")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .spec(SpecBuilder.getResponseSpec(200))
                .extract().body().as(GetBookByAuthorResponse[].class);

        Assert.assertTrue(response.length > 0);

    }
}
