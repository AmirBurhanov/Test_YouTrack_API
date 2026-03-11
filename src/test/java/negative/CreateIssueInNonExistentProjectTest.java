package negative;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateIssueInNonExistentProjectTest extends BaseTest {

  @negative
  @Test(description = "Негативный тест: создание задачи в несуществующем проекте")
  public void createIssueInNonExistentProject() {
    CreateIssueRequest request = new CreateIssueRequest(
        "Task in wrong project",
        "Description",
        "non-existent-id");

    int statusCode = RestAssured
        .given()
        .spec(spec)
        .body(request)
        .when()
        .post("/issues")
        .then()
        .extract()
        .statusCode();

    assertThat("Ожидался 400 Not Found", statusCode, equalTo(400));
    Reporter.log("✓ Негативный тест: создание задачи в несуществующем проекте -> " + statusCode, true);
  }
}
