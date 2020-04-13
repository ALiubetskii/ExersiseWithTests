import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class FoldersOperations {

    @BeforeEach
    void configureApi() {
        RestAssured.baseURI = "https://cloud-api.yandex.net:443/v1/disk/resources";
        RestAssured.requestSpecification = given()
                .header("Content-Type", "application/json")
                .header("Authorization","AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
    }

    @Test
    void createDeleteFolder() {

        String pathForDelete =
        given()
                .when()
                .put("path", "myTestDiskForAutomation")
                .then()
                .assertThat()
                .statusCode(201)
                .body("href", notNullValue())
                .extract()
                .path("href");


        given()
                .when()
                .queryParam("path", pathForDelete)
                .delete()
                .then()
                .assertThat()
                .statusCode(204);
    }

}
