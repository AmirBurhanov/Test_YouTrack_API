package positive;

import client.IssueGetEndpoint;
import org.testng.annotations.Test;

public class FilterIssuesTest {

  @Test(description = "Поиск задач по тексту")
  public void searchIssuesByText() {
    IssueGetEndpoint.searchIssues("Test");
  }
}
