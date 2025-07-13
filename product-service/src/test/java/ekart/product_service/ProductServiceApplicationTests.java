package ekart.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(ProductServiceApplicationTests.class);

//	@Test
//	void contextLoads() {
//	}

	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");



	//as out application will run on random port, it will inject that port into it, then this we will use in rest URLs
	@LocalServerPort
	private Integer port;


	//to start db container
	static {
		postgreSQLContainer.start();
	}


	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;

        log.debug("Random port is: {}", port);
	}





	@Test
	void shouldCreateProduct(){


		//multiline string, introduced in java 14
		String requestBody = """
				{
					"name" : "iPhone 16",
					"description" : "Latest iPhone from apple",
					"price" : "70000"
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("name", Matchers.equalTo("iPhone 16")) //matchers from hamcrest
				;

	}

}
