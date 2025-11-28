package org.umangqa.library.builders;

import org.umangqa.library.pojo.addbook.AddBookRequest;
import org.umangqa.library.pojo.deletebook.DeleteBookRequest;
import org.umangqa.library.utils.TestUtils;

public class TestDatabuilder {

    public static AddBookRequest addBookRequestPayload(String name, String isbn, String aisle, String author) {

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName(name);
        addBookRequest.setIsbn(isbn);
        addBookRequest.setAisle(aisle);
        addBookRequest.setAuthor(author);

        return addBookRequest;
    }

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

    public static DeleteBookRequest deleteBookRequestPayload(String bookId) {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest();
        deleteBookRequest.setID(bookId);

        return deleteBookRequest;
    }

}
