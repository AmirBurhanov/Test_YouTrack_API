package endpoints;

import base.BaseTest;
import io.restassured.RestAssured;
import utils.EndpointsConfig;
import org.testng.Reporter;
import constants.TestConstants;

public final class IssueDeleteEndpoint extends BaseEndpoint {

  private IssueDeleteEndpoint() {
  }

  public static void deleteIssue(String issueId) {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, issueId)
        .when()
        .delete(EndpointsConfig.getIssueByIdEndpoint())
        .then()
        .statusCode(200);

    Reporter.log("Задача удалена: " + issueId, true);
  }
}
