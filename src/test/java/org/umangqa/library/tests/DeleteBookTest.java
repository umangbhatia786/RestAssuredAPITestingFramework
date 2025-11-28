package org.umangqa.library.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.umangqa.library.builders.TestDatabuilder;
import org.umangqa.library.pojo.deletebook.DeleteBookRequest;
import org.umangqa.library.specs.SpecBuilder;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Epic("Library API")
@Feature("Delete Book")
public class DeleteBookTest extends BaseTest {

    @Story("Delete book by ID")
    @Description("Checks if a previously added book can be deleted successfully")
    @Test(dependsOnMethods = "org.umangqa.library.tests.AddBookTest.addBookTest", priority = 3)
    public void deleteBookTest(){
        step("Build DeleteBook payload");
        DeleteBookRequest payload = TestDatabuilder.deleteBookRequestPayload(AddBookTest.createdBookId);
        step("Call DeleteBook API and verify response");
        Response response = given().log().all()
                .body(payload)
                .when()
                .post("/Library/DeleteBook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .extract().response();

    }

}
