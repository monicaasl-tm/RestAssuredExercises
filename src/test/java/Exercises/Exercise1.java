package Exercises;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Exercise1 {
	
	
	@BeforeTest
	private void startTesting() {
		RestAssured.baseURI = "https://api.github.com"; 
	}

	
	
	//1
		@Test
		public void getUser() {

			given().pathParam("user", "monicaasl-tm")
			.when().get("/users/{user}")
			.then().log().all().assertThat().statusCode(200);
			
		}
		
	//2
		@Test
		public void getRepos() {

			given()
			.when().get("/users/monicaasl-tm/repos")
			.then().log().all().assertThat().statusCode(200);
			
		}
		
		
	//3
		@Test
		public void showThreeRepos() {

			given().param("q", "cucumber").param("per_page", 3)
			.when().get("/search/repositories")
			.then().log().all().assertThat().statusCode(200);
			
		}
		
	//4
		@Test
		public void getOrgRepos() {

			given()
			.when().get("/orgs/apple/repos")
			.then().log().all().assertThat().statusCode(200);
			
		}
		
	//5
		@Test
		public void searchRepo	() {

			given()
			.when().get("/repos/torvalds/linux")
			.then().log().all().assertThat().statusCode(200);
			
		}
		
}
