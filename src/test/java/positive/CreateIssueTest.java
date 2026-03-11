package positive;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import dto.response.IssueResponse;
import org.testng.annotations.Test;
import utils.TestConfig;
import static io.restassured.RestAssured.given;

public class CreateIssueTest extends BaseTest {

    @Test
    public void createIssueSuccessfully() {

        CreateIssueRequest request =
                new CreateIssueRequest(
                        "Test summary2",
                        "Test description",
                        TestConfig.getProjectId()
                );

        IssueResponse response =
                given()
                        .spec(spec)
                        .body(request)
                        .log().all()
                        .when()
                        .post("/issues")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(IssueResponse.class);
        assert response.getId() != null;
    }
}