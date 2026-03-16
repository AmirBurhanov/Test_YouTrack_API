package client;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.TestConfig;
import utils.EndpointsConfig;
import org.testng.Reporter;
import constants.TestConstants;

public final class IssuePostEndpoint extends BaseEndpoint {

  private IssuePostEndpoint() {
  }

  public static void createIssue(String summary, String description) {
    CreateIssueRequest request = new CreateIssueRequest(summary, description, PROJECT_ID);

    IssueResponse response = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFields())
        .body(request)
        .when()
        .post(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.created())
        .extract()
        .as(IssueResponse.class);

    Reporter.log("Задача создана: " + response.getIdReadable(), true);
  }
}
