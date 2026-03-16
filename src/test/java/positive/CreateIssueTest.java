package positive;

import endpoints.IssuePostEndpoint;
import org.testng.annotations.Test;

public class CreateIssueTest {

  @Test(description = "Создание задачи")
  public void createIssueSuccessfully() {
    IssuePostEndpoint.createIssue("Test summary", "Test description");
  }
}
