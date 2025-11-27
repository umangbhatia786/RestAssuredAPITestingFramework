package org.umangqa.library.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.umangqa.library.builders.TestDatabuilder;
import org.umangqa.library.pojo.deletebook.DeleteBookRequest;
import org.umangqa.library.specs.SpecBuilder;

import static io.restassured.RestAssured.given;


public class DeleteBookTest extends BaseTest {

    @Test(dependsOnMethods = "org.umangqa.library.tests.AddBookTest.addBookTest", priority = 3)
    public void deleteBookTest(){
        DeleteBookRequest payload = TestDatabuilder.deleteBookRequestPayload(AddBookTest.createdBookId);

        Response response = given().log().all()
                .body(payload)
                .when()
                .post("/Library/DeleteBook.php")
                .then().log().all()
                .spec(SpecBuilder.getResponseSpec(200))
                .extract().response();

    }

}
