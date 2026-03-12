package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

public class UpdateIssueDescription extends BaseTest {

  @Test(description = "Обновление описания существующей задачи")
  public void updateIssueDescription() {
    String issueId = createTestIssue("Issue for Update Test");
    String newDescription = "Updated description";

    IssueResponse response = RestAssured
        .given()
        .spec(SpecRequest.getRequestSpec())
        .body("{\"description\": \"" + newDescription + "\"}")
        .pathParam("id", issueId)
        .queryParam("fields", "id,description")
        .when()
        .post(TestConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .as(IssueResponse.class);

    Assert.assertEquals(response.getDescription(), newDescription);
    Reporter.log("Описание задачи " + issueId + " обновлено", true);
  }
}
