package utils;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderYT {

  @DataProvider(name = "validIssueData", parallel = true)
  public static Object[][] provideValidIssueData() {
    return readCsvData("src/test/resources/test-data.csv", true);
  }

  @DataProvider(name = "allIssueData", parallel = true)
  public static Object[][] provideAllIssueData() {
    return readCsvData("src/test/resources/test-data.csv", false);
  }

  private static Object[][] readCsvData(String csvFile, boolean onlyPositive) {
    List<Object[]> testData = new ArrayList<>();
    String line;
    String csvSplitBy = ",";

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      String header = br.readLine();
      if (header == null) {
        throw new RuntimeException("CSV файл пуст: " + csvFile);
      }

      while ((line = br.readLine()) != null) {
        String[] data = line.split(csvSplitBy);
        if (data.length >= 3) {
          String summary = data[0].trim();
          String description = data[1].trim();
          int expectedStatus;
          try {
            expectedStatus = Integer.parseInt(data[2].trim());
          } catch (NumberFormatException e) {
            Reporter.log("Некорректный статус в строке: " + line, true);
            continue;
          }

          if (!onlyPositive || expectedStatus == 201) {
            testData.add(new Object[] { summary, description, expectedStatus });
          }
        } else {
          Reporter.log("Пропущена некорректная строка: " + line, true);
        }
      }
    } catch (IOException e) {
      String errorMsg = "Ошибка чтения файла " + csvFile + ": " + e.getMessage();
      Reporter.log(errorMsg, true);
      throw new RuntimeException(errorMsg, e);
    }

    if (testData.isEmpty()) {
      String errorMsg = "Нет данных в CSV файле (возможно, не найдены строки с expectedStatus=201)";
      Reporter.log(errorMsg, true);
      throw new RuntimeException(errorMsg);
    }

    return testData.toArray(new Object[0][]);
  }
}
