import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetInfoAboutTrash {

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
    void fileSizeComparison() throws InterruptedException {

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

        System.out.println("=====Post file_1=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto/cvety_cherep_7642.jpg")
                .queryParam("url", "https://avatarko.ru/img/kartinka/8/cvety_cherep_7642.jpg")
                .post("/resources/upload")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(202);

        System.out.println("\n");

        System.out.println("=====Post file_2=====");
        given()
                .when()
                .queryParam("path", "myTestDiskForAuto/s1200")
                .queryParam("url", "https://avatars.mds.yandex.net/get-pdb/2412470/2394617b-e894-4725-8c10-f6aba217e3e9/s1200")
                .post("/resources/upload")
                .then()
                .log().status().log().body()
                .assertThat().statusCode(202);

//        System.out.println("\n");
//
//        System.out.println("=====File Size In The Trash=====");
//        given()
//                .when()
//                .queryParam("path", "trash:/")
//                .get("/trash/resources")
//                .then()
//                .log().status().log().body()
//                .assertThat().statusCode(200)
//                .extract().path("")
    }


}
