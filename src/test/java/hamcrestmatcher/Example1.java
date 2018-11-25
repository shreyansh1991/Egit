package hamcrestmatcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Example1 extends Base {

	/**
	 * This test is to verify the occurence of userID attribute.
	 */
	@Test()
	public void validateGetAlbumStatusCode1() {
		given()
		.when()
		.get("/albums")
		.then()
		.log()
		.status()
		.and()
		.statusCode(200)
		.body("userId.size()",equalTo(100))
		.body("id.size()",equalTo(100))
		.body("title.size()",equalTo(100));
		
		System.out.println("passed");

	}

	@Test()
	public void validateFirstTitle() {
		given()
		.when()
		.get("/albums")
		.then()
		.log()
		.status()
		.and()
		.statusCode(200)
		.body("title[0]",equalTo("quidem molestiae enim"));
		
		System.out.println("passed");

	}
	
	@Test()
	public void validateFirstTitle1() {
	Response res=	given()
		.when()
		.get("/albums/1")
		.then()
		.log()
		.body()
		.extract()
		.response();
	
		
		
		
	

	}
	
	@Test()
	public void getTitleWithQ() {
	List<String> list=given()
		.when()
		.get("/albums")
		.then()
		
		.extract()
		.path("findAll{it.title==~/q.*/}");
	System.out.println(list);
	System.out.println(list.size());
	
	
	
	}
}
