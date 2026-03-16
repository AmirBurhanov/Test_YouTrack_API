package negative;

import client.IssueNegativeEndpoint;
import org.testng.annotations.Test;

public class GetIssueNegativeTest {

  @Test(description = "Получение несуществующей задачи")
  public void getNonExistingIssue() {
    IssueNegativeEndpoint.getNonExistentIssue();
  }
}
