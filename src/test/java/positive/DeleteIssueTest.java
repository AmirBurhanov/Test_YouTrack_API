package positive;

import client.IssueDeleteEndpoint;
import client.IssueTestDataHelper;
import org.testng.annotations.Test;

public class DeleteIssueTest {

  @Test(description = "Удаление существующей задачи")
  public void deleteExistingIssue() {
    String issueId = IssueTestDataHelper.createTestIssueAndReturnId("Issue to delete");
    IssueDeleteEndpoint.deleteIssue(issueId);
  }
}
