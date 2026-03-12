package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

public class CreateIssueNegativeTest extends BaseTest {

  @Test(description = "Попытка создать задачу без summary")
  public void createIssueWithoutSummary() {
    CreateIssueRequest request = new CreateIssueRequest(
        null,
        "Description without summary",
        TestConfig.getProjectId());

    RestAssured.given()
        .spec(SpecRequest.getRequestSpec())
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверено создание задачи без summary", true);
  }
}
