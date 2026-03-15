package negative;

import client.IssueClient;
import org.testng.annotations.Test;

public class EmptyCommentAdd {

  @Test(description = "Добавление пустого комментария")
  public void addEmptyComment() {
    IssueClient.addEmptyComment();
  }
}
