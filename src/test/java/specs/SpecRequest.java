package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.TestConfig;

public class SpecRequest {

  private static final RequestSpecification REQUEST_SPEC;

  static {
    REQUEST_SPEC = new RequestSpecBuilder()
        .setBaseUri(TestConfig.getBaseUrl())
        .setBasePath(TestConfig.getBasePath())
        .addHeader("Authorization", "Bearer " + TestConfig.getApiToken())
        .setContentType(ContentType.JSON)
        .build();
  }

  public static RequestSpecification getRequestSpec() {
    return REQUEST_SPEC;
  }
}
