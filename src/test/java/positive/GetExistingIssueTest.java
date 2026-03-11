package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.TestConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetExistingIssueTest extends BaseTest {

  @Test(description = "Получение существующей задачи по ID")
  public void getExistingIssue() {
    String issueId = createTestIssueAndGetId();

    IssueResponse response = RestAssured
        .given()
        .spec(spec)
        .pathParam("issueId", issueId)
        .queryParam("fields", "id,idReadable,summary,description")
        .when()
        .get("/issues/{issueId}")
        .then()
        .log().all()
        .statusCode(200)
        .extract()
        .as(IssueResponse.class);

    assertThat("ID задачи не должен быть null", response.getId(), notNullValue());
    assertThat("Readable ID не должен быть null", response.getIdReadable(), notNullValue());

    Reporter.log("✓ Успешно получена задача", true);
    Reporter.log("  ID: " + response.getId(), true);
    Reporter.log("  Readable ID: " + response.getIdReadable(), true);
    Reporter.log("  Summary: " + response.getSummary(), true);
  }

  private String createTestIssueAndGetId() {
    String summary = "Test Issue for Get Test " + System.currentTimeMillis();

    String requestBody = "{"
        + "\"summary\":\"" + summary + "\","
        + "\"project\":{\"id\":\"" + TestConfig.getProjectId() + "\"}"
        + "}";

    Response response = RestAssured
        .given()
        .spec(spec)
        .body(requestBody)
        .when()
        .post("/issues")
        .then()
        .statusCode(200)
        .extract()
        .response();

    String issueId = response.path("id");
    String issueReadableId = response.path("idReadable");

    Reporter.log("Создана тестовая задача: " + issueReadableId, true);
    return issueId;
  }

  @Test(description = "Получение несуществующей задачи (негативный)")
  public void getNonExistingIssue() {
    String nonExistingId = "non-existing-id-12345";

    int statusCode = RestAssured
        .given()
        .spec(spec)
        .pathParam("issueId", nonExistingId)
        .when()
        .get("/issues/{issueId}")
        .then()
        .log().ifError()
        .extract()
        .statusCode();

    assertThat("Ожидался статус 404", statusCode, equalTo(404));
    Reporter.log("✓ Корректная ошибка 404 при запросе несуществующей задачи", true);
  }

  @Test(description = "Получение задачи по readable ID")
  public void getIssueByReadableId() {
    String summary = "Test Issue with Readable ID " + System.currentTimeMillis();

    Response createResponse = RestAssured
        .given()
        .spec(spec)
        .queryParam("fields", "id,idReadable,summary")
        .body("{"
            + "\"summary\":\"" + summary + "\","
            + "\"project\":{\"id\":\"" + TestConfig.getProjectId() + "\"}"
            + "}")
        .when()
        .post("/issues")
        .then()
        .log().all()
        .statusCode(200)
        .extract()
        .response();

    String readableId = createResponse.path("idReadable");
    assertThat("readableId не должен быть null", readableId, notNullValue());

    IssueResponse response = RestAssured
        .given()
        .spec(spec)
        .pathParam("issueId", readableId)
        .queryParam("fields", "id,idReadable,summary,description")
        .when()
        .get("/issues/{issueId}")
        .then()
        .statusCode(200)
        .extract()
        .as(IssueResponse.class);

    assertThat("Readable ID должен совпадать", response.getIdReadable(), equalTo(readableId));
    Reporter.log("✓ Успешно получена задача по readable ID: " + readableId, true);
  }
}
