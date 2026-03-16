package negative;

import client.CommentEndpoint;
import client.IssueTestDataHelper;
import org.testng.annotations.Test;

public class EmptyCommentAdd {

  @Test(description = "Добавление пустого комментария")
  public void addEmptyComment() {
    String issueId = IssueTestDataHelper.createTestIssueAndReturnId("Issue for empty comment");
    CommentEndpoint.addEmptyComment(issueId);
  }
}
