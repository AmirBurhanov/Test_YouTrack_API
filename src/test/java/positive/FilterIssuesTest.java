package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.TestConfig;

import java.util.List;

public class FilterIssuesTest extends BaseTest {

  @Test(description = "Получение списка задач проекта")
  public void getAllIssuesForProject() {
    List<IssueResponse> issues = RestAssured
        .given()
        .spec(spec)
        .queryParam("fields", "id,idReadable,summary") // ← ДОБАВЬ ЭТО
        .queryParam("project", TestConfig.getProjectId())
        .when()
        .get("/issues")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Найдено " + issues.size() + " задач", true);
  }

  @Test(description = "Поиск задач по тексту")
  public void searchIssuesByText() {
    String searchQuery = "Test";

    List<IssueResponse> issues = RestAssured
        .given()
        .spec(spec)
        .queryParam("query", searchQuery)
        .when()
        .get("/issues")
        .then()
        .statusCode(200)
        .extract()
        .as(new TypeRef<List<IssueResponse>>() {
        });

    Reporter.log("Найдено задач по запросу '" + searchQuery + "': " + issues.size(), true);
  }
}
