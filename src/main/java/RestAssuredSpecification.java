import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class RestAssuredSpecification {

    @BeforeEach
    void requestConfig() {

        RestAssured.baseURI = "https://cloud-api.yandex.net:443/";
        RestAssured.requestSpecification = given()
                .header("Content-Type", "application/json")
                .header("Authorization","AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
    }
}
