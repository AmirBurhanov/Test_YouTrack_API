package endpoints;

import base.BaseTest;
import dto.request.CommentRequest;
import io.restassured.RestAssured;
import specs.SpecRespons;
import utils.EndpointsConfig;
import org.testng.Reporter;
import constants.TestConstants;

public final class CommentEndpoint extends BaseEndpoint {

  private CommentEndpoint() {
  }

  public static void addEmptyComment(String issueId) {
    RestAssured
        .given()
        .spec(SPEC)
        .body(new CommentRequest(""))
        .pathParam(TestConstants.ID_PARAM, issueId)
        .when()
        .post(EndpointsConfig.getIssueCommentsEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверка: добавление пустого комментария -> 400", true);
  }
}
