import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FoldersOperations {

    @BeforeEach
    void configureApi() {
        RestAssured.baseURI = "https://cloud-api.yandex.net/v1/disk/resources";
        RestAssured.requestSpecification = given()
                .header("Authorization","AgAAAAAr4rfPAADLW8AAA6UeHUg6g4OvNNoQeHw");
    }

    @Test
    void createDeleteFolder() {

        given()
                .log().everything()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .put()
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("href", notNullValue());

        System.out.println("\n\n\n");

        given()
                .log().everything()
                .when()
                .queryParam("path", "myTestDiskForAuto")
                .queryParam("permanently", true)
                .delete()
                .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(202), is(204)));

        System.out.println("\n\n\n");

        given()
                .log().everything()
                .when()
                .queryParam("path","myTestDiskForAuto")
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }
}
