import io.qameta.allure.Allure;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExampleTestSuite {
    PrintStream printToFile;
    String fileLocation;
    String fileName;

    @BeforeClass
    public void setup() {
        baseURI = System.getProperty("host");
        port = Integer.parseInt(System.getProperty("port"));
    }

    @BeforeTest
    public void preconditions() throws FileNotFoundException {
        fileLocation = System.getProperty("user.dir") + "/build/tmp";
        fileName = System.currentTimeMillis() + ".log";
        File outputFile = new File(fileLocation, fileName);
        FileOutputStream fileOutput = new FileOutputStream(outputFile);
        printToFile = new PrintStream(fileOutput);
    }

    @AfterTest
    public void attachments() throws IOException {
        Path content = Paths.get(fileLocation + "\\" + fileName);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("logs", is);
        }
    }

    @Test(description = "test 1")
    public void test1() {
        given().filters(new RequestLoggingFilter(printToFile), new ResponseLoggingFilter(printToFile))
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
