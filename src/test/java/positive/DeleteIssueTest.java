package positive;

import client.IssueClient;
import org.testng.annotations.Test;

public class DeleteIssueTest {

  @Test(description = "Удаление существующей задачи")
  public void deleteExistingIssue() {
    String issueId = IssueClient.createTestIssueAndReturnId("Issue to delete");

    IssueClient.deleteIssue(issueId);
  }
}
