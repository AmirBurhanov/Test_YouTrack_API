package positive;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddCommentToIssue extends BaseTest {
    @Test(description = "Добавление комментария к задаче")
    public void addCommentToIssue() {
        String issueId = "1-1";
        String comment = "This is a test comment from Amir";

        int statusCode = RestAssured.given()
                .spec(spec)
                .body("{\"text\": \"" + comment + "\"}")
                .post("/api/issues/{id}/comments", issueId)
                .then()
                .extract()
                .statusCode();

        Assert.assertEquals(statusCode, 201);
        Reporter.log("Added comment to issue " + issueId, true);
    }
}
