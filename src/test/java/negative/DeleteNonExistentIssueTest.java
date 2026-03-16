package negative;

import client.IssueNegativeEndpoint;
import org.testng.annotations.Test;

public class DeleteNonExistentIssueTest {

  @Test(description = "Удаление несуществующей задачи")
  public void deleteNonExistentIssue() {
    IssueNegativeEndpoint.deleteNonExistentIssue();
  }
}
