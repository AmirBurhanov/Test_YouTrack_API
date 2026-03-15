package negative;

import client.IssueClient;
import org.testng.annotations.Test;

public class GetIssueNegativeTest {

  @Test(description = "Получение несуществующей задачи")
  public void getNonExistingIssue() {
    IssueClient.getNonExistentIssue();
  }
}
