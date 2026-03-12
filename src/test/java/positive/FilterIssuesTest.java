package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

import java.util.List;

public class FilterIssuesTest extends BaseTest {

  @Test(description = "Поиск задач по тексту")
  public void searchIssuesByText() {
    String searchQuery = "Test";

    List<IssueResponse> issues = RestAssured
        .given()
        .spec(SpecRequest.getRequestSpec())
        .queryParam("query", searchQuery)
        .queryParam("fields", "id,idReadable,summary")
        .when()
        .get(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .jsonPath()
        .getList(".", IssueResponse.class);

    Reporter.log("Найдено задач по запросу '" + searchQuery + "': " + issues.size(), true);
  }
}
