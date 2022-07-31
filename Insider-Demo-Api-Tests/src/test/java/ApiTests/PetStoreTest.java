package ApiTests;


import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class PetStoreTest {

    @BeforeClass
    public void before() {
        baseURI = "https://petstore.swagger.io/v2";
    }


    @Test
    public void getPetsByStatusAvailable() {

        given().log().all().accept(ContentType.JSON).
                queryParam("status", "available").
                when().get("/pet/findByStatus").then().
                statusCode(200).
                assertThat().contentType(equalTo("application/json"));

    }

    @Test
    public void getPetsByStatusPending() {

        given().log().all().accept(ContentType.JSON).
                queryParam("status", "pending").
                when().get("/pet/findByStatus").then().
                statusCode(200).
                assertThat().contentType(equalTo("application/json"));

    }

    @Test
    public void getPetsByStatusSold() {

        given().log().all().accept(ContentType.JSON).
                queryParam("status", "sold").
                when().get("/pet/findByStatus").then().
                statusCode(200).
                assertThat().contentType(equalTo("application/json"));
    }

    @Test
    public void getPetById() {

        given().log().all().accept(ContentType.JSON).
                pathParam("id", 9258433).
                when().get("/pet/{id}").then().
                statusCode(200).
                assertThat().contentType(equalTo("application/json")).
                body("name", equalTo("Amal"),
                        "status", equalTo("available"),
                        "id", equalTo(9258433));
    }


    //negative scenario
    @Test
    public void findPetById() {
        //provided an invalid id and test result--> HttpResponseException: Not Found
        given().log().all().accept(ContentType.JSON).
                pathParam("id", 0).
                when().get("/pet/{id}").then().
                statusCode(404);

    }
}