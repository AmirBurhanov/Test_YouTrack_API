package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specs.SpecRequest;
import utils.TestConfig;

public class BaseTest {

  protected RequestSpecification spec;

  @BeforeMethod
  public void setup() {
    spec = SpecRequest.getRequestSpec();
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  protected String createTestIssue(String summary) {
    String requestBody = "{"
        + "\"summary\":\"" + summary + "\","
        + "\"project\":{\"id\":\"" + TestConfig.getProjectId() + "\"}"
        + "}";

    return RestAssured
        .given()
        .spec(spec)
        .body(requestBody)
        .queryParam("fields", "id")
        .when()
        .post(TestConfig.getIssuesEndpoint())
        .then()
        .statusCode(200)
        .extract()
        .path("id");
  }
}
