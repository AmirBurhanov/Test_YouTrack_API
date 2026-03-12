package negative;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

public class GetIssueNegativeTest extends BaseTest {

  @Test(description = "Негативный тест: получение несуществующей задачи")
  public void getNonExistingIssue() {
    String invalidId = "0-0";

    RestAssured.given()
        .spec(SpecRequest.getRequestSpec())
        .pathParam("id", invalidId)
        .when()
        .get(TestConfig.getIssueByIdEndpoint())
        .then()
        .spec(SpecRespons.notFound());

    Reporter.log("Проверен несуществующий ID: " + invalidId, true);
  }
}
