package negative;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class GetIssueNegativeTest extends BaseTest {

    @Test(description = "Негативный тест: получение несуществующей задачи")
    public void getNonExistingIssue() {
        String invalidId = "0-0";

        int statusCode = RestAssured.given()
                .spec(spec)
                .get("/api/issues/{id}", invalidId)
                .then()
                .log().ifValidationFails()
                .extract()
                .statusCode();

        assert statusCode == 404 : "Expected 404 but got " + statusCode;

        Reporter.log("Attempted to retrieve non-existing issue ID: " + invalidId, true);
        Reporter.log("Received status code: " + statusCode, true);
    }
}