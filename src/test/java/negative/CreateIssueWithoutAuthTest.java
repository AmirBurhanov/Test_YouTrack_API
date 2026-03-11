package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.TestConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateIssueWithoutAuthTest extends BaseTest {

    @Test(description = "Негативный тест: создание задачи без авторизации")
    public void createIssueWithoutAuth() {
        CreateIssueRequest request = new CreateIssueRequest(
                "Task without auth",
                "Description",
                TestConfig.getProjectId()
        );

        int statusCode = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUrl())
                .basePath("/api")
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/issues")
                .then()
                .extract()
                .statusCode();

        assertThat("Ожидался 401 Unauthorized", statusCode, equalTo(401));
        Reporter.log("✓ Негативный тест: создание задачи без авторизации -> " + statusCode, true);
    }
}
