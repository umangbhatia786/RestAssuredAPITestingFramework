package org.umangqa.library.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.umangqa.library.pojo.getbook.byAuthor.GetBookByAuthorResponse;
import org.umangqa.library.pojo.getbook.byID.GetBookByIdResponse;
import org.umangqa.library.specs.SpecBuilder;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Library API")
@Feature("Get Book Details")
public class GetBookTest extends BaseTest {
    @Story("Retrieve book by ID")
    @Description("Validates that the book details can be fetched using the stored Book ID")
    @Test(dependsOnMethods = "org.umangqa.library.tests.AddBookTest.addBookTest", priority = 1)
    public void getBookByIdTest() {

        step("Retrieve stored Book ID");
        String currentBookID = AddBookTest.createdBookId;

        step("Call GetBook API with ID");
        GetBookByIdResponse[] response = given().log().all()
                .queryParam("ID", currentBookID)
                .when()
                .get("/Library/GetBook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .body(matchesJsonSchemaInClasspath("schemas/getBookByIdResponse.json"))
                .extract().body().as(GetBookByIdResponse[].class);

        step("Validate GetBookById response fields");
        Assert.assertTrue(response.length > 0, "No book returned for ID: " + AddBookTest.createdBookId);
        GetBookByIdResponse book = response[0];
        Assert.assertEquals(book.getIsbn() + book.getAisle(), AddBookTest.createdBookId);
    }

    @Story("Retrieve book by Author")
    @Description("Validates that book details can be fetched using author name")
    @Test(priority = 2)
    public void getBookByAuthorTest() {
        step("Retrieve stored Author name");
        String author = "John Doe";

        step("Call GetBook API with AuthorName");
        GetBookByAuthorResponse[] response = given().log().all()
                .queryParam("AuthorName", author)
                .when()
                .get("/Library/GetBook.php")
                .then()
                .spec(SpecBuilder.getResponseSpec(200))
                .extract().body().as(GetBookByAuthorResponse[].class);

        step("Validate GetBookByAuthor response fields");
        Assert.assertTrue(response.length > 0);

    }
}
