package negative;

import endpoints.IssueNegativeEndpoint;
import org.testng.annotations.Test;

public class CreateIssueInNonExistentProjectTest {

  @Test(description = "Создание задачи в несуществующем проекте")
  public void createIssueInNonExistentProject() {
    IssueNegativeEndpoint.createIssueInNonExistentProject();
  }
}
