package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.TestConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateIssueWithInvalidTokenTest extends BaseTest {

    @Test(description = "Негативный тест: создание задачи с неверным токеном")
    public void createIssueWithInvalidToken() {
        CreateIssueRequest request = new CreateIssueRequest(
                "Task with invalid token",
                "Description",
                TestConfig.getProjectId()
        );

        int statusCode = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUrl())
                .basePath("/api")
                .header(new Header("Authorization", "Bearer invalid.token.here"))
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/issues")
                .then()
                .extract()
                .statusCode();

        assertThat("Ожидался 401 Unauthorized", statusCode, equalTo(401));
        Reporter.log("✓ Негативный тест: создание задачи с неверным токеном -> " + statusCode, true);
    }
}
