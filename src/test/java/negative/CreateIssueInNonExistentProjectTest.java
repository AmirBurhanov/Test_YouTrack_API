package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import utils.TestConfig;
import specs.SpecRespons;
import specs.SpecRequest;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateIssueInNonExistentProjectTest extends BaseTest {

  @Test(description = "Негативный тест: создание задачи в несуществующем проекте")
  public void createIssueInNonExistentProjectWithSpec() {
    CreateIssueRequest request = new CreateIssueRequest(
        "Task in wrong project",
        "Description",
        "non-existent-id");

    RestAssured
        .given()
        .spec(spec)
        .body(request)
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("✓ Негативный тест: создание задачи в несуществующем проекте -> 400", true);
  }
}
