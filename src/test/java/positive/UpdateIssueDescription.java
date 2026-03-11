package positive;

import base.BaseTest;
import dto.response.IssueResponse;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UpdateIssueDescription extends BaseTest {

    @Test(description = "Обновление описания существующей задачи")
    public void updateIssueDescription() {
        String issueId = "1-1";
        String newDescription = "Updated description";

        IssueResponse response = RestAssured.given()
                .spec(spec)
                .body("{\"description\": \"" + newDescription + "\"}")
                .patch("/api/issues/{id}?fields=id,description", issueId)
                .then()
                .statusCode(200)
                .extract()
                .as(IssueResponse.class);

        Assert.assertEquals(response.getDescription(), newDescription);
        Reporter.log("Updated description: " + response.getDescription(), true);
    }
}