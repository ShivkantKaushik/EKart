package ekart.inventory_service;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

//	@Test
//	void contextLoads() {
//	}


	@LocalServerPort
	Integer port;


	@BeforeEach
    public void	setUp () {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	public void testInStock(){

	   var resBody = RestAssured.given()
			   .queryParam("skuCode", "pixel_8").queryParam("quantity", 90)
			   .when()
			   .get("/api/inventory")
			   .then()
			   .log().all()
			   .extract()
			   .body()
			   .asString();


        assertEquals("true", resBody);

	}



}
