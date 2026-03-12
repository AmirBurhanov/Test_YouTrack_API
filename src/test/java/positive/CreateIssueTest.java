package positive;

import base.BaseTest;
import dto.request.CreateIssueRequest;
import dto.response.IssueResponse;
import org.testng.annotations.Test;
import specs.SpecRequest;
import specs.SpecRespons;
import utils.TestConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateIssueTest extends BaseTest {

  @Test(description = "Создание задачи с минимальными полями")
    public void createIssueSuccessfully() {
        String summary = "Test summary " + System.currentTimeMillis();
        CreateIssueRequest request = new CreateIssueRequest(
                summary,
                "Test description",
                TestConfig.getProjectId()
        );

        IssueResponse response = given()
                .spec(SpecRequest.getRequestSpec())
                .body(request)
                .queryParam("fields", "id,idReadable,summary")
                .when()
                .post(TestConfig.getIssuesEndpoint())
                .then()
                .spec(SpecRespons.success())
                .extract()
                .as(IssueResponse.class);

        assertThat(response.getIdReadable(), notNullValue());
    }
}
