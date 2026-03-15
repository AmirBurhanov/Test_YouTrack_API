package base;

import io.restassured.specification.RequestSpecification;
import specs.SpecRequest;
import utils.TestConfig;

public class BaseTest {

  protected static final String PROJECT_ID = TestConfig.getProjectId();
  protected static final String INVALID_PROJECT_ID = "non-existent-id";
  protected static final String INVALID_ISSUE_ID = "0-0";

  protected static RequestSpecification SPEC;
  protected static RequestSpecification UNAUTHORIZED_SPEC;

  static {
    SPEC = SpecRequest.getRequestSpec();
    UNAUTHORIZED_SPEC = SpecRequest.getUnauthorizedSpec();
    System.out.println("✅ Static auth initialized");
  }
}
