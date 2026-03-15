package positive;

import client.IssueClient;
import org.testng.annotations.Test;

public class GetAllIssuesTest {

  @Test(description = "Получение всех задач")
  public void getAllIssues() {
    IssueClient.getAllIssues();
  }
}
