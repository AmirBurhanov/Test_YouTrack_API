package negative;

import endpoints.AuthEndpoint;
import org.testng.annotations.Test;

public class CreateIssueWithoutAuthTest {

  @Test(description = "Негативный тест: создание задачи без авторизации")
  public void createIssueWithoutAuth() {
    AuthEndpoint.createIssueWithoutAuth("Task without auth", "Description");
  }
}
