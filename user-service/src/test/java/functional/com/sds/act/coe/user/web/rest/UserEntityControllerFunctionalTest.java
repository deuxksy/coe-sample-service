package functional.com.sds.act.coe.user.web.rest;

import com.sds.act.coe.user.UserApplication;
import com.sds.act.coe.user.repository.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserEntityControllerFunctionalTest {

    @LocalServerPort
    private int port = 8050;

    @Test
    public void POST_registerCustomer() {
        UserEntity userEntity = new UserEntity("Bradley", "aaa@gmail.com");
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userEntity)
                .when()
                .post("/api/v1/customers")
                .then()
                .statusCode(201)
                .assertThat()
                .body("name", equalTo("Bradley"));
    }

    @Test
    public void GET_getCustomer() {
        given()
                .port(port)
                .when()
                .get("/api/v1/customers/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Adam"))
                .body("_self", isEmptyOrNullString());
    }

    @Test
    public void GET_getAllCustomers() {
        given()
                .port(port)
                .when()
                .get("/api/v1/customers")
                .then()
                .statusCode(200)
                .assertThat()
                .body("$.size()", greaterThanOrEqualTo(7))
                .body("name", hasItems("Adam", "John", "Smith", "Edgar", "Martin", "Tom", "Sean"))
                .body("email", hasItems(
                        "adam@boot.com",
                        "john@boot.com",
                        "smith@boot.com",
                        "edgar@boot.com",
                        "martin@boot.com",
                        "tom@boot.com",
                        "sean@boot.com"
                ));
    }
}
