import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ExampleTestSuite {
    @BeforeClass
    public void setup() {
        baseURI = System.getProperty("host");
        port = Integer.parseInt(System.getProperty("port"));
        filters(new AllureRestAssured());
    }

    public static Response searchBreweries(String parameter, int expectedStatusCode) {
        return given().when().get("/breweries/search?query=" + parameter).then().statusCode(expectedStatusCode).extract().response();
    }

    @Test(description = "Search for specific brewery")
    public void test1() {
        List<Integer> idList = searchBreweries("Diving Dog Brewhouse", 200).jsonPath().getList("id");

        assertThat(idList.size(), equalTo(1));
        assertThat(idList.get(0), equalTo(530));
    }

    @Test(description = "SQL injection")
    public void test2() {
        List<Integer> idList = searchBreweries("dog'+OR+1=1--", 200).jsonPath().getList("id");

        assertThat(idList.size(), equalTo(0));
    }

    @Test(description = "Empty search term")
    public void test3() {
        List<Integer> idList = searchBreweries("", 200).jsonPath().getList("id");

        assertThat(idList.size(), equalTo(0));
    }

    @Test(description = "Unexpected query string parameter")
    public void test4() {
        List<Integer> idList = given().when().get("/breweries/search?term=dog").then().statusCode(200).extract().response().jsonPath().getList("id");

        assertThat(idList.size(), equalTo(0));
    }

    @Test(description = "No query string parameters specified")
    public void test5() {
        List<Integer> idList = given().when().get("/breweries/search").then().statusCode(200).extract().response().jsonPath().getList("id");

        assertThat(idList.size(), equalTo(0));
    }
}
