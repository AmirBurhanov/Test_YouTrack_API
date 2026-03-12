package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRespons;
import utils.TestConfig;

public class CreateIssueWithoutAuthTest extends BaseTest {

  @Test(description = "Негативный тест: создание задачи без авторизации")
  public void createIssueWithoutAuth() {
    CreateIssueRequest request = new CreateIssueRequest(
        "Task without auth",
        "Description",
        TestConfig.getProjectId());

    RestAssured.given()
        .baseUri(TestConfig.getBaseUrl())
        .basePath(TestConfig.getBasePath())
        .header("Content-Type", "application/json")
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.unauthorized());

    Reporter.log("✓ Проверено создание задачи без авторизации", true);
  }
}
