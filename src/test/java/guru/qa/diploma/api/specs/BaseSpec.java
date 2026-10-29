package guru.qa.diploma.api.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class BaseSpec {


    public static final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://httpbin.org")
            .setBasePath("/")
            .setContentType(JSON)
            .addFilter(new AllureRestAssured())
            .build();
}