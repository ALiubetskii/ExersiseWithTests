import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Token {

    @Test
    public void getAccessToken() {

        given()
                .baseUri("https://oauth.yandex.ru/authorize")
                .queryParam("response_type", "token")
                .queryParam("client_id", "a931c425f11441d19150e05ef7dd705b")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);

    }

}
