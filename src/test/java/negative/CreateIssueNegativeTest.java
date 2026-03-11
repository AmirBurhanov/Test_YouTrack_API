package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.TestConfig;

public class CreateIssueNegativeTest extends BaseTest {

  @Test(description = "Попытка создать задачу без summary")
  public void createIssueWithoutSummary() {
    CreateIssueRequest request = new CreateIssueRequest(
        null,
        "Description without summary",
        TestConfig.getProjectId());

    RestAssured.given()
        .spec(spec)
        .body(request)
        .when()
        .post("/issues")
        .then()
        .statusCode(400)
        .log().all();

    Reporter.log("Тест: создание задачи без summary завершен", true);
  }
}
