package utils;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.*;

public class CsvDataProvider {

  @DataProvider(name = "issueData")
  public static Object[][] getIssueData() throws IOException {

    List<Object[]> data = new ArrayList<>();

    BufferedReader br = new BufferedReader(
        new FileReader("src/test/resources/issue-data.csv"));

    br.readLine();

    String line;
    while ((line = br.readLine()) != null) {
      String[] values = line.split(",");
      data.add(new Object[] {
          values[0],
          values[1],
          Integer.parseInt(values[2])
      });
    }

    br.close();

    return data.toArray(new Object[0][]);
  }
}
