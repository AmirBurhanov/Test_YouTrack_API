package positive;

import client.IssueClient;
import org.testng.annotations.Test;

public class FilterIssuesTest {

  @Test(description = "Поиск задач по тексту")
  public void searchIssuesByText() {
    IssueClient.searchIssues("Test");
  }
}
