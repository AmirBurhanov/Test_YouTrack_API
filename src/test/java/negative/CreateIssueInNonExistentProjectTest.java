package negative;

import client.IssueClient;
import org.testng.annotations.Test;

public class CreateIssueInNonExistentProjectTest {

  @Test(description = "Создание задачи в несуществующем проекте")
  public void createIssueInNonExistentProject() {
    IssueClient.createIssueInNonExistentProject();
  }
}
