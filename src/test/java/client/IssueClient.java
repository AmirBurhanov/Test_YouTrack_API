package client;

import base.BaseTest;
import dto.request.CommentRequest;
import dto.request.CreateIssueRequest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.TestConfig;
import org.testng.Assert;
import org.testng.Reporter;
import constants.TestConstants;
import java.util.List;

public class IssueClient extends BaseTest {

  public static void createIssue(String summary, String description) {
    CreateIssueRequest request = new CreateIssueRequest(summary, description, PROJECT_ID);

    IssueResponse response = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFields())
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.created())
        .extract()
        .as(IssueResponse.class);

    Reporter.log("Задача создана: " + response.getIdReadable(), true);
  }

  public static void getIssue(String issueId) {
    if (issueId == null || issueId.isEmpty()) {
      issueId = createTestIssueAndReturnId("Test Issue");
    }

    IssueResponse response = RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.FIELDS_PARAM, issueId)
        .queryParam("fields", TestConfig.getIssueFields())
        .when()
        .get(TestConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .as(IssueResponse.class);

    Reporter.log("Задача получена: " + response.getIdReadable(), true);
  }

  public static void searchIssues(String searchQuery) {
    List<IssueResponse> issues = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.QUERY_PARAM, searchQuery)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFieldsSearch())
        .when()
        .get(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Найдено задач по запросу '" + searchQuery + "': " + issues.size(), true);
  }

  public static void createIssueWithoutSummary() {
    CreateIssueRequest request = new CreateIssueRequest(null, "Description", PROJECT_ID);

    RestAssured
        .given()
        .spec(SPEC)
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: создание задачи без summary 400", true);
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
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: создание задачи в несуществующем проекте 400", true);
  }

  public static void createIssueWithoutAuth(String summary, String description) {
    CreateIssueRequest request = new CreateIssueRequest(summary, description, PROJECT_ID);

    RestAssured.given()
        .spec(UNAUTHORIZED_SPEC)
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.unauthorized());

    Reporter.log("Проверка: создание задачи без авторизации 401", true);
  }

  public static void getNonExistentIssue() {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, INVALID_ISSUE_ID)
        .when()
        .get(TestConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.notFound());

    Reporter.log("Проверка: получение несуществующей задачи 404", true);
  }

  public static void addEmptyComment() {
    String issueId = createTestIssueAndReturnId("Issue for empty comment");

    RestAssured
        .given()
        .spec(SPEC)
        .body(new CommentRequest(""))
        .pathParam(TestConstants.ID_PARAM, issueId)
        .when()
        .post(TestConfig.getIssueCommentsEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: добавление пустого комментария 400", true);
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
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.created())
        .extract()
        .path(TestConstants.ID_PATH);
  }

  public static void deleteIssue(String issueId) {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, issueId)
        .when()
        .delete(TestConfig.getIssueByIdEndpoint())
        .then()
        .statusCode(200);

    Reporter.log("Задача удалена: " + issueId, true);
  }

  public static void deleteNonExistentIssue() {
    RestAssured
        .given()
        .spec(SPEC)
        .pathParam(TestConstants.ID_PARAM, INVALID_ISSUE_ID)
        .when()
        .delete(TestConfig.getIssueByIdEndpoint())
        .then()
        .statusCode(404);

    Reporter.log("Проверка: удаление несуществующей задачи 404", true);
  }

  public static void getAllIssues() {
    List<IssueResponse> issues = RestAssured
        .given()
        .spec(SPEC)
        .queryParam(TestConstants.FIELDS_PARAM, TestConfig.getIssueFieldsSearch())
        .when()
        .get(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Получен список задач. Найдено: " + issues.size(), true);
  }
}
