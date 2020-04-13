import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FoldersOperations {

    RestAssuredSpecification spec = new RestAssuredSpecification();
//    @BeforeEach
//    void configureApi() {
//        RestAssured.baseURI = "https://cloud-api.yandex.net:443/";
//        RestAssured.requestSpecification = given()
//                .header("Content-Type", "application/json")
//                .header("Authorization","AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
//    }

    @Test
    void createDeleteFolder() {

        given()
                .spec(spec)
                .when()
                .put("path", "myTestDiskForAutomation")
                .then()
                .assertThat()
                .statusCode(201);
    }
}
