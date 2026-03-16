package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;

public class EndpointsConfig {

  private static final String ENDPOINTS_FILE = "src/test/resources/endpoints/endpoints.properties";
  private static Properties endpoints = new Properties();

  static {
    loadEndpoints();
  }

  private static void loadEndpoints() {
    try (FileInputStream fis = new FileInputStream(ENDPOINTS_FILE)) {
      endpoints.load(fis);
      Reporter.log("Эндпоинты загружены из " + ENDPOINTS_FILE, true);
    } catch (IOException e) {
      String errorMsg = "Ошибка загрузки эндпоинтов: " + e.getMessage();
      Reporter.log(errorMsg, true);
      throw new RuntimeException(errorMsg, e);
    }
  }

  public static String getIssuesEndpoint() {
    return endpoints.getProperty("issues", "/issues");
  }

  public static String getIssueByIdEndpoint() {
    return endpoints.getProperty("issue.by.id", "/issues/{id}");
  }

  public static String getIssueCommentsEndpoint() {
    return endpoints.getProperty("issue.comments", "/issues/{id}/comments");
  }

  public static String getProjectsEndpoint() {
    return endpoints.getProperty("projects", "/projects");
  }

  // Удобные методы с подстановкой ID
  public static String getIssueByIdEndpoint(String issueId) {
    return getIssueByIdEndpoint().replace("{id}", issueId);
  }

  public static String getIssueCommentsEndpoint(String issueId) {
    return getIssueCommentsEndpoint().replace("{id}", issueId);
  }
}
