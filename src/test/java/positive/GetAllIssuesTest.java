package positive;

import client.IssueGetEndpoint;
import org.testng.annotations.Test;

public class GetAllIssuesTest {

  @Test(description = "Получение всех задач")
  public void getAllIssues() {
    IssueGetEndpoint.getAllIssues();
  }
}
