package utils;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.testng.Reporter;

public class AuthUtils {

  public static RequestSpecification getAuthForUser(String targetUsername) {
    String token = readTokenFromCsv(targetUsername);

    return given()
        .header("Authorization", "Bearer " + token)
        .contentType(ContentType.JSON);
  }

  private static String readTokenFromCsv(String targetUsername) {
    String csvFile = "src/test/resources/users.csv";
    String line;

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

      br.readLine();

      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        if (data[0].trim().equals(targetUsername)) {
          return data[1].trim();
        }
      }

    } catch (IOException e) {
      Reporter.log("Ошибка чтения users.csv: " + e.getMessage(), true);
      throw new RuntimeException(e);
    }

    throw new RuntimeException("Пользователь не найден: " + targetUsername);
  }
}
