package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import specs.SpecRequest;

public class BaseTest {

  protected RequestSpecification spec;

  @BeforeMethod
  public void setup() {
    spec = SpecRequest.getRequestSpec();
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
}
