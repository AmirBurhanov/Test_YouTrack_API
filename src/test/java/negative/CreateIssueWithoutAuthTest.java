package negative;

import client.IssueClient;
import org.testng.annotations.Test;

public class CreateIssueWithoutAuthTest {

  @Test(description = "Негативный тест: создание задачи без авторизации")
  public void createIssueWithoutAuth() {
    IssueClient.createIssueWithoutAuth("Task without auth", "Description");
  }
}
