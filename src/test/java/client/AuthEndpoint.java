package client;

import dto.request.CreateIssueRequest;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.EndpointsConfig;
import org.testng.Reporter;

public final class AuthEndpoint extends BaseEndpoint {

  private AuthEndpoint() {
  }

  public static void createIssueWithoutAuth(String summary, String description) {
        CreateIssueRequest request = new CreateIssueRequest(
            summary, 
            description, 
            getProjectId()
        );

        RestAssured.given()
            .spec(getUnauthorizedSpec())
            .body(request)
            .when()
            .post(EndpointsConfig.getIssuesEndpoint())
            .then()
            .spec(SpecRespons.unauthorized());

        Reporter.log("Проверка: создание задачи без авторизации -> 401", true);
    }
}
