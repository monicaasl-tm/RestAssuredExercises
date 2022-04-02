package Exercises;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Exercise2 {
	
	
	@BeforeTest
	private void startTesting() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2"; 
	}

	//Find pets by status
	@Test
	public void getPetStatus() {

		given().header("accept","application/json")
		.when().get("pet/findByStatus?status=available")
		.then().log().all().statusCode(200).extract().response();
		
	}
	
	//Get the name of the pet with an ID
	@Test
	public void getPet() {

		Response response = given().header("accept","application/json")
		.pathParam("petId", 1)
		.when().get("/pet/{petId}")
		.then().assertThat().statusCode(200).extract().response();
		JsonPath json = new JsonPath(response.asString());
		System.out.println(json.getString("name"));
		
	}
	
	
	//Add a new pet to the store
	@Test
	public void addNewPet() {
		given().header("accept","application/json").header("Content-Type","application/json")
		.body("{\n"
				+ "  \"id\": 0,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 0,\n"
				+ "    \"name\": \"string\"\n"
				+ "  },\n"
				+ "  \"name\": \"doggie\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 0,\n"
				+ "      \"name\": \"string\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"available\"\n"
				+ "}")
		.when().post("/pet")
		.then().log().all().assertThat().statusCode(200);
	}
	
	//Place an order for a pet
		@Test
		public void orderPet() {
			given().header("accept","application/json").header("Content-Type","application/json")
			.body("{\n"
					+ "  \"id\": 0,\n"
					+ "  \"petId\": 0,\n"
					+ "  \"quantity\": 0,\n"
					+ "  \"shipDate\": \"2022-03-29T04:00:04.360Z\",\n"
					+ "  \"status\": \"placed\",\n"
					+ "  \"complete\": true\n"
					+ "}")
			.when().post("/store/order")
			.then().log().all().assertThat().statusCode(200);
		}
		
	//Create a user
		@Test
		public void createUser() {
			given().header("accept","application/json").header("Content-Type","application/json")
			.body("{\n"
					+ "  \"id\": 689,\n"
					+ "  \"username\": \"monicasl\",\n"
					+ "  \"firstName\": \"Monica\",\n"
					+ "  \"lastName\": \"Sanchez\",\n"
					+ "  \"email\": \"monica@example.com\",\n"
					+ "  \"password\": \"jhsgjkhagjkwa\",\n"
					+ "  \"phone\": \"6692486367\",\n"
					+ "  \"userStatus\": 0\n"
					+ "}")
			.when().post("/user")
			.then().log().all().assertThat().statusCode(200);
			
			
			Response responseGet = given().pathParam("username", "monicasl")
					.when()
					.get("/user/{username}").then().log().all()
					.assertThat().statusCode(200).extract().response();
			
			JsonPath json = new JsonPath(responseGet.asString());
			Assert.assertEquals(json.getString("firstName"), "Monica");
			
		}
		
		//Update a user
			@Test
			public void updateUser() {
				given().header("accept","application/json").header("Content-Type","application/json")
				.body("{\n"
						+ "  \"id\": 689,\n"
						+ "  \"username\": \"monicasl93\",\n"
						+ "  \"firstName\": \"Monica\",\n"
						+ "  \"lastName\": \"Sanchez\",\n"
						+ "  \"email\": \"monica@example.com\",\n"
						+ "  \"password\": \"jhsgjkhagjkwa\",\n"
						+ "  \"phone\": \"6692486367\",\n"
						+ "  \"userStatus\": 0\n"
						+ "}")
				.pathParam("username", "monicasl")
				.when().put("/user/{username}")
				.then().log().all().assertThat().statusCode(200);
				

				Response responseGet = given().pathParam("username", "monicasl93")
						.when()
						.get("/user/{username}").then().log().all()
						.assertThat().statusCode(200).extract().response();
				
				JsonPath json = new JsonPath(responseGet.asString());
				Assert.assertEquals(json.getString("firstName"), "Monica");
			}
			
		//Delete a user
			@Test
			public void deleteUser() {
				given().header("accept","application/json").header("Content-Type","application/json")
				.pathParam("username", "monicasl93")
				.when().delete("/user/{username}")
				.then().log().all().assertThat().statusCode(200);
			}



}
