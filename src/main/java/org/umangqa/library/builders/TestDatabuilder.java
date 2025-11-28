package org.umangqa.library.builders;

import io.qameta.allure.Step;
import org.umangqa.library.pojo.addbook.AddBookRequest;
import org.umangqa.library.pojo.deletebook.DeleteBookRequest;
import org.umangqa.library.utils.TestUtils;

public class TestDatabuilder {

    @Step("Building AddBook Request Payload with user provided params")
    public static AddBookRequest addBookRequestPayload(String name, String isbn, String aisle, String author) {

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName(name);
        addBookRequest.setIsbn(isbn);
        addBookRequest.setAisle(aisle);
        addBookRequest.setAuthor(author);

        return addBookRequest;
    }

    @Step("Building AddBook Request Payload with random generated data")
    public static AddBookRequest addBookRequestRandomPayload() {
        String isbn = "ISBN" + TestUtils.generateRandomString(4);
        String aisle = TestUtils.randomNonZeroNumberString(4);

        AddBookRequest addBookRandomRequest = new AddBookRequest();

        addBookRandomRequest.setName("Learn Rest Assured with Java");
        addBookRandomRequest.setIsbn(isbn);
        addBookRandomRequest.setAisle(aisle);
        addBookRandomRequest.setAuthor("John Doe");

        return addBookRandomRequest;

    }

    @Step("Building DeleteBook Payload with ID: {bookId}")
    public static DeleteBookRequest deleteBookRequestPayload(String bookId) {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest();
        deleteBookRequest.setID(bookId);

        return deleteBookRequest;
    }

}
