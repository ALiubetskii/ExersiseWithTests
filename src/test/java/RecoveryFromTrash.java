import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecoveryFromTrash {

    @BeforeEach
    void requestConfig() {

        RestAssured.baseURI = "https://cloud-api.yandex.net/v1/disk";
        RestAssured.requestSpecification = given()
                .log().method()
                .log().uri()
                .log().body()
                .header("Authorization", "AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
    }

    @Test
    void recoveryFile() throws InterruptedException {

        System.out.println("=====Create folder=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .put("/resources")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(201)
                .body("href", notNullValue());

        System.out.println("\n");

        System.out.println("=====Post file=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto/cvety_cherep_7642.jpg")
                .queryParam("url", "https://avatarko.ru/img/kartinka/8/cvety_cherep_7642.jpg")
                .post("/resources/upload")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(202);

        System.out.println("\n");

        System.out.println("=====Get md5=====");
        ArrayList md5File =
                given()
                        .when()
                        .queryParam("path", "myTestDiskForAuto")
                        .get("/resources")
                        .then()
                        .log().status().log().body()
                        .assertThat().statusCode(200)
                        .extract().path("_embedded.items.md5");

        System.out.println("\n");

        Thread.sleep(2500);

        System.out.println("=====Delete file=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto/cvety_cherep_7642.jpg")
                .queryParam("md5", md5File)
                .queryParam("permanently", false)
                .delete("/resources")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(anyOf(is(202), is(204)));

        System.out.println("\n");

        System.out.println("=====Recovery File=====");
        given()
                .when()
                .queryParam("path", "cvety_cherep_7642.jpg")
                .queryParam("name", "cvety_cherep_7642_recovered.jpg")
                .put("/trash/resources/restore")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(201);

        System.out.println("\n");

        System.out.println("=====Delete Folder=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .queryParam("permanently", true)
                .delete("/resources")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(anyOf(is(202), is(204)));

    }
}
