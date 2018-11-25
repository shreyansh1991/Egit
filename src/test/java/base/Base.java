package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Base {

	@BeforeClass
	public static void setUp() {
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/config.properties");
			properties.load(input);
			System.out.println("Url from properties file - " + properties.getProperty("testurl"));
			RestAssured.baseURI = properties.getProperty("testurl");
			System.out.println(" RestAssured.baseURI - "+ RestAssured.baseURI);
			// point to remember is default value for RestAssured.baseURI is http://localhost

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
