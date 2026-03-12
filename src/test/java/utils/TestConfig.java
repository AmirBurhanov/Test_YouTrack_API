package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;

public class TestConfig {

  private static final String CONFIG_FILE = "src/test/resources/config.properties";
  private static Properties properties = new Properties();

  static {
    loadProperties();
  }

  private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            Reporter.log("Конфигурация загружена из " + CONFIG_FILE, true);
        } catch (IOException e) {
            String errorMsg = "Ошибка загрузки конфигурации:" + e.getMessage();
            Reporter.log(errorMsg, true);
            throw new RuntimeException(errorMsg, e);
        }
    }

  public static String getProjectId() {
    return properties.getProperty("project.id", "TEST");
  }

  public static String getBaseUrl() {
    return properties.getProperty("base.url");
  }

  public static String getApiToken() {
    return properties.getProperty("api.token");
  }

  public static String getBasePath() {
    return properties.getProperty("base.path");
  }

  public static String oauthRedirect() {
    return properties.getProperty("oauth.redirect.uri");
  }

  public static String getFullBaseUrl() {
    return getBaseUrl() + getBasePath();
  }

  public static String getIssuesEndpoint() {
    return properties.getProperty("endpoint.issues", "/issues");
  }

  public static String getIssueByIdEndpoint() {
    return properties.getProperty("endpoint.issue.by.id", "/issues/{id}");
  }

  public static String getIssueCommentsEndpoint() {
    return properties.getProperty("endpoint.issue.comments", "/issues/{id}/comments");
  }

  public static String getIssueCommentsEndpoint(String issueId) {
    return getIssueCommentsEndpoint().replace("{id}", issueId);
  }

  public static String getIssueByIdEndpoint(String issueId) {
    return getIssueByIdEndpoint().replace("{id}", issueId);
  }
}
