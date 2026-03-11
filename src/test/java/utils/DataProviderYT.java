package utils;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderYT {

  @DataProvider(name = "validIssueData", parallel = true)
  public static Object[][] provideValidIssueData() {
    return readCsvData("src/test/resources/issue-data.csv", true);
  }

  @DataProvider(name = "allIssueData", parallel = true)
  public static Object[][] provideAllIssueData() {
    return readCsvData("src/test/resources/issue-data.csv", false);
  }

  private static Object[][] readCsvData(String csvFile, boolean onlyPositive) {
    List<Object[]> testData = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      br.readLine(); // пропускаем заголовок

      String line;
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        String summary = data[0].trim();
        String description = data[1].trim();
        int expectedStatus = Integer.parseInt(data[2].trim());

        if (!onlyPositive || expectedStatus == 200) {
          testData.add(new Object[] { summary, description, expectedStatus });
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Ошибка чтения CSV: " + csvFile, e);
    }

    return testData.toArray(new Object[0][]);
  }
}
