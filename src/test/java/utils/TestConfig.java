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
  private static RequestSpecification requestSpec;

  static {
    loadProperties();
  }

  private static void loadProperties() {
    try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
      properties.load(fis);
      Reporter.log("Конфигурация загружена из " + CONFIG_FILE, true);
    } catch (IOException e) {
      String error = "Ошибка загрузки конфигурации: " + e.getMessage();
      Reporter.log(error, true);
      throw new RuntimeException(error, e);
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
}
