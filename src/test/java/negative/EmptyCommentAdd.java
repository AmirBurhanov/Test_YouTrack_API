package negative;

import base.BaseTest;
import dto.request.CommentRequest;
import io.restassured.RestAssured;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

public class EmptyCommentAdd extends BaseTest {

  @Test(description = "Добавление пустого комментария")
  public void addEmptyComment() {
    String issueId = createTestIssue("Issue for empty comment test");

    RestAssured.given()
        .spec(SpecRequest.getRequestSpec())
        .body(new CommentRequest(""))
        .pathParam("id", issueId)
        .when()
        .post(TestConfig.getIssueCommentsEndpoint())
        .then()
        .spec(SpecRespons.badRequest());

    Reporter.log("Проверен пустой комментарий", true);
  }
}
