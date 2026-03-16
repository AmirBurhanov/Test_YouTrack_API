package endpoints;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.EndpointsConfig;
import org.testng.Reporter;
import constants.TestConstants;

public final class IssueNegativeEndpoint extends BaseEndpoint {

  private IssueNegativeEndpoint() {
  }

  public static void createIssueWithoutSummary() {
    CreateIssueRequest request = new CreateIssueRequest(null, "Description", PROJECT_ID);

    RestAssured
        .given()
        .spec(SPEC)
        .body(request)
        .when()
        .post(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: создание задачи без summary -> 400", true);
  }

  public static void createIssueInNonExistentProject() {
    CreateIssueRequest request = new CreateIssueRequest(
        "Task in wrong project",
        "Description",
        INVALID_PROJECT_ID);

    RestAssured
        .given()
        .spec(SPEC)
        .body(request)
        .when()
        .post(EndpointsConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: создание задачи в несуществующем проекте -> 400", true);
  }

  public static void getNonExistentIssue() {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, INVALID_ISSUE_ID)
        .when()
        .get(EndpointsConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.notFound());

    Reporter.log("Проверка: получение несуществующей задачи -> 404", true);
  }

  public static void deleteNonExistentIssue() {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, INVALID_ISSUE_ID)
        .when()
        .delete(EndpointsConfig.getIssueByIdEndpoint())
        .then()
        .statusCode(404);

    Reporter.log("Проверка: удаление несуществующей задачи -> 404", true);
  }
}
