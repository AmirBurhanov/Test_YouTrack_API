package positive;

import base.BaseTest;
import dto.request.CommentRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

public class AddCommentToIssue extends BaseTest {

  @Test(description = "Добавление комментария к задаче")
  public void addCommentToIssue() {
    String issueId = createTestIssue("Issue for comment test");
    String commentText = "Test comment from API";

    CommentRequest commentRequest = new CommentRequest(commentText);

    Response response = RestAssured
        .given()
        .spec(SpecRequest.getRequestSpec())
        .body(commentRequest)
        .pathParam("id", issueId)
        .queryParam("fields", "id,text,author(login)")
        .when()
        .post(TestConfig.getIssueCommentsEndpoint())
        .then()
        .spec(SpecRespons.success())
        .extract()
        .response();

    String commentId = response.path("id");
    Assert.assertNotNull(commentId);

    Reporter.log("Комментарий добавлен ID: " + commentId, true);
  }
}
