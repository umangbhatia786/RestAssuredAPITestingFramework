package org.umangqa.library.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.umangqa.library.specs.SpecBuilder;

public abstract class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.requestSpecification = SpecBuilder.getRequestSpec();
    }
}
