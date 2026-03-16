package endpoints;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.EndpointsConfig;
import utils.TestConfig;
import constants.TestConstants;

public final class IssueTestDataHelper extends BaseEndpoint {

  private IssueTestDataHelper() {
  }

  public static String createTestIssueAndReturnId(String summary) {
    CreateIssueRequest request = new CreateIssueRequest(
        summary,
        "Test description",
        PROJECT_ID);

    return RestAssured
        .given()
        .spec(SPEC)
        .body(request)
        .queryParam(TestConstants.FIELDS_PARAM, TestConstants.ID_PARAM)
        .when()
        .post(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.created())
        .extract()
        .path(TestConstants.ID_PATH);
  }
}
