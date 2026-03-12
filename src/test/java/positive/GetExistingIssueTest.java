package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetExistingIssueTest extends BaseTest {

  @Test(description = "Получение существующей задачи по ID")
  public void getExistingIssue() {
    String issueId = createTestIssue("Test Issue for Get Test");

    IssueResponse response = RestAssured
        .given()
        .spec(SpecRequest.getRequestSpec())
        .pathParam("id", issueId)
        .queryParam("fields", "id,idReadable,summary,description")
        .when()
        .get(TestConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .as(IssueResponse.class);

    assertThat(response.getIdReadable(), notNullValue());

    Reporter.log("zадача" + response.getIdReadable() + " получена", true);
  }
}
