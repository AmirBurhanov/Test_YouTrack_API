package client;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.TestConfig;
import utils.EndpointsConfig;
import org.testng.Reporter;
import constants.TestConstants;
import java.util.List;

public final class IssueGetEndpoint extends BaseEndpoint {

  private IssueGetEndpoint() {
  }

  public static void getIssue(String issueId) {
    if (issueId == null || issueId.isEmpty()) {
      issueId = IssueTestDataHelper.createTestIssueAndReturnId("Test Issue");
    }

    IssueResponse response = RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, issueId)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFields())
        .when()
        .get(EndpointsConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .as(IssueResponse.class);

    Reporter.log("Задача получена: " + response.getIdReadable(), true);
  }

  public static void getAllIssues() {
    List<IssueResponse> issues = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFieldsSearch())
        .when()
        .get(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Получен список задач. Найдено: " + issues.size(), true);
  }

  public static void searchIssues(String searchQuery) {
    List<IssueResponse> issues = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.QUERY_PARAM, searchQuery)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFieldsSearch())
        .when()
        .get(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Найдено задач по запросу '" + searchQuery + "': " + issues.size(), true);
  }
}
