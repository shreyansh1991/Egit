import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.path.json.JsonPath.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import base.Base;
import groovy.time.BaseDuration.From;
import groovy.transform.stc.FromAbstractTypeMethods;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utility.utils;

public class TestClass1 extends Base {

	Response response;
	/** Response is a interface which extends other interfaces ResponseBody<Response>,
	ResponseOptions<Response>, Validatable<ValidatableResponse, Response>
	 **/
	@Test(enabled=true)
	public void validateGetAlbumStatusCode() {
		response = given()
				.when()
				.get("/albums")
				.then()
				.log()
				.status()
				.extract()
				.response();
		System.out.println(response.asString());
		
		Assert.assertTrue(response.getStatusCode() == 200, "Status Code Mismatch...");
		System.out.println("Status Code Match..");
		from(response.asString()).getList("");
		
		
	}

	@Test(enabled = false)
	public void validateGetAlbumStatusCode1() {
		given().when().get("/albums").then().log().status().and().assertThat().statusCode(200).and()
				.header("Content-Type", "application/json; charset=utf-8");

	}

	@Test(enabled=false)
	public void validateAlbumSize() {
		response = utils.GET_Response("/albums");
		/*
		 * $.[*].id $ is a root node , [] is array , [*] array of anything followed by
		 * id
		 * 
		 */

		List<Integer> list = JsonPath.read(response.asString(), "$.[*].id");
		List<String> title = JsonPath.read(response.asString(), "$.[*].title");
		
		System.out.println("size - "+list.size());
		System.out.println("title - "+title.size());
		System.out.println("data is "+list.get(0));
	//	Assert.assertEquals(Integer.parseInt(list.get(0)), 1);
		System.out.println("Passed");
		//Assert.assertTrue(list.size() == 100, "Album size didn't matched");
		// response.jsonPath().getList("$.[*].id").size()
	}
	
	@Test(enabled = false)
	public void getData() {
		response = utils.GET_Response("/posts");
		/*
		 * $.[*].id $ is a root node , [] is array , [*] array of anything followed by
		 * id
		 * 
		 */

		// https://github.com/json-path/JsonPath            refer this link
		 List<String> list1 = JsonPath.read(response.asString(), "$..title"); 
		 List<String> list2 = JsonPath.read(response.asString(), "$.[*].title"); 
		 
		 for(int i=0;i<list1.size();i++)
		 {
			 System.out.println("data "+list1.get(i)+"  ::  "+list2.get(i));
		 }
		//System.out.println("size - "+list.size());
		
		/*io.restassured.path.json.JsonPath jp=
				new io.restassured.path.json.JsonPath(response.asString());
		System.out.println(jp.get("title[6]"));
		*/
		
		/*String title = JsonPath.read(response.asString(),"");
	System.out.println("Title - "+title);*/
		//System.out.println("title size - "+title.size());
		//Assert.assertTrue(list.size() == 100, "Album size didn't matched");
		// response.jsonPath().getList("$.[*].id").size()
	}
	
	
	

	@Test(enabled=false)
	public void validateAlbumSize1() {
		response = utils.GET_Response("/albums");

		// Using Rest Assured JsonPath Class
		io.restassured.path.json.JsonPath jp = new io.restassured.path.json.JsonPath(response.asString());
		List<String> l = jp.get("id");

		Assert.assertEquals(l.size(), 100, "Mismatch..");

		/*
		 * $.[*].id $ is a root node , [] is array , [*] array of anything followed by
		 * id
		 * 
		 */
		// using jayway JsonPath class;
		List<String> list = JsonPath.read(response.asString(), "$.[*].id");
		Assert.assertTrue(list.size() == 100, "Album size didn't matched");
		// response.jsonPath().getList("$.[*].id").size()
	}

	
	
	@Test(enabled=false)
	public void validateFirstTitle() {
		response = utils.GET_Response("/albums");
		
		

		// Using Rest Assured JsonPath Class
		io.restassured.path.json.JsonPath jp = new io.restassured.path.json.JsonPath(response.asString());
		String s= jp.get("title[0]");
System.out.println("s" +s);
		

		/*
		 * $.[*].id $ is a root node , [] is array , [*] array of anything followed by
		 * id
		 * 
		 */
		// using jayway JsonPath class;
		List<String> list = JsonPath.read(response.asString(), "$.[*].id");
		
		Assert.assertTrue(list.size() == 100, "Album size didn't matched");
		// response.jsonPath().getList("$.[*].id").size()
	}
	
	@Test(enabled = false)
	public void demo() {
		response = utils.GET_Response("/posts");
		// can be dynamic, based on another end point response
		List<String> list = JsonPath.read(response.asString(), "$.[*].title");
		System.out.println(list);
	}
	
	
	@Test(enabled = false)
	public void validateUsersCompany() {
		response = utils.GET_Response("/users");
		String fremo=response.asString();
		// can be dynamic, based on another end point response
		List<String> list = JsonPath.read(response.asString(), "$.[*].[?(@.name=='Leanne Graham')].website");
		Assert.assertEquals(list.get(0), "Romaguera-Crona");
	}

	// hamcrest matcher example
	@Test(enabled = false)
	public void testPhotosContainSpecificContent() {
		given().contentType(ContentType.JSON).expect().body("url[0]", equalTo("http://placehold.it/600/92c952"))
				.body("url[4999]", equalTo("http://placehold.it/600/6dd9cb")).when().get("/photos");
	}

}
