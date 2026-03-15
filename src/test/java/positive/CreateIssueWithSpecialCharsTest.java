package positive;

import client.IssueClient;
import org.testng.annotations.Test;

public class CreateIssueWithSpecialCharsTest {

  @Test(description = "Создание задачи с специальными символами")
  public void createIssueWithSpecialChars() {
    String specialChars = "Описание с спецсимволами: !@#$%^&*()_+{}[]|\\:;\"'<>?,./";
    String summary = "Задача !@#$%^&*()";

    IssueClient.createIssue(summary, specialChars);
  }
}
