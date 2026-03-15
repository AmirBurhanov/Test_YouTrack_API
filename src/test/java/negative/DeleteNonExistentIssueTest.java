package negative;

import client.IssueClient;
import org.testng.annotations.Test;

public class DeleteNonExistentIssueTest {

  @Test(description = "Удаление несуществующей задачи")
  public void deleteNonExistentIssue() {
    IssueClient.deleteNonExistentIssue();
  }
}
