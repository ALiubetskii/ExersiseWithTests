import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class FileOperations {

    @BeforeEach
    void apiConfig() {

        RestAssured.baseURI = "https://cloud-api.yandex.net/v1/disk/resources";
        RestAssured.requestSpecification = given()
                .log().everything()
                .header("Authorization","AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
    }

    @Test
    void createDeleteFile() {

        given()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .put()
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("href", notNullValue());

        System.out.println("\n\n");

        String pathToPic =
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .queryParam("url", "https://avatarko.ru/kartinka/1606")
                .post("/upload")
                .then()
                .log().all()
                .assertThat()
                .statusCode(202)
                .extract()
                .path("href");

        System.out.println("\n\n");

        given()
                .log().everything()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .queryParam("md5", "")
                .delete()
                .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(202), is(204)));
    }


}
