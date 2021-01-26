import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ExampleTestSuite {
    @BeforeClass
    public void setup() {
        baseURI = System.getProperty("host");
        port = Integer.parseInt(System.getProperty("port"));
    }

    @Test(description = "test 1")
    public void test1() {
        given()
                .log().all()
                .when().get("/breweries/search?query=dog")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("data.leagueId", equalTo(35));
    }

    @Test(description = "test 2")
    public void test2() {
        Assert.assertEquals(1, 1);
    }

    @Test(description = "test 3")
    public void test3() {
        Assert.assertEquals(1, 1);
    }

    @Test(description = "test 4")
    public void test4() {
        Assert.assertEquals(1, 1);
    }

    @Test(description = "test 5")
    public void test5() {
        Assert.assertEquals(1, 1);
    }
}
