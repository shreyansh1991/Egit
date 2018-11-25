import static io.restassured.RestAssured.given;

import static io.restassured.path.json.JsonPath.from;

import java.util.List;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.utils;



public class TestClass3 extends Base {
	/**
	 * This test is to find out all the id's whose title starts with q
	 * 
	 */
	@Test
	public void test3() {
		// Rest Assured is implemented in groovy library.
		// items.findAll{it.name=='Shreyansh Jain'}
		// items.findAll{it.name==~/Ref.*/} this is to findout the name starts with Ref

		List<String> list = given()
				.when()
				.get("/posts")
				.then()
				.extract()
				
				.path("findAll{it.title==~/q.*/}.id");
		System.out.println("List		" + list);
		
		Response response=	utils.GET_Response("/posts");
		
		JsonPath jp=new JsonPath(response.asString());
		//This test is to find out all the id's whose title starts with q
			List<String> list2=jp.get("findAll{it.title==~/q.*/}");
			System.out.println("List 2 "+ list2);
			int size=jp.get("id.size()");
			System.out.println(size);
			
			JsonPath jp2=new JsonPath(response.asString());
			List<String> l=jp2.get("findAll{it.body==~/a.*/}");
				System.out.println("Body with aut "+l);
			
			
			
			
	}
}
