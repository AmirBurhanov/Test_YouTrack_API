package endpoints;

import base.BaseTest;
import io.restassured.specification.RequestSpecification;

public abstract class BaseEndpoint extends BaseTest {

  protected static RequestSpecification getSpec() {
    return SPEC;
  }

  protected static RequestSpecification getUnauthorizedSpec() {
    return UNAUTHORIZED_SPEC;
  }

  protected static String getProjectId() {
    return PROJECT_ID;
  }

  protected static String getInvalidProjectId() {
    return INVALID_PROJECT_ID;
  }

  protected static String getInvalidIssueId() {
    return INVALID_ISSUE_ID;
  }
}
