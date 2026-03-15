package positive;

import client.IssueClient;
import org.testng.annotations.Test;

public class CreateIssueTest {

  @Test(description = "Создание задачи с минимальными полями")
  public void createIssueSuccessfully() {
    IssueClient.createIssue("Test summary", "Test description");
  }
}
