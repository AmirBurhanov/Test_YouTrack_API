package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utils.TestConfig;

public class BaseTest {

  protected RequestSpecification spec;

  @BeforeMethod
  public void setup() {
    spec = TestConfig.getRequestSpec();

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
}
