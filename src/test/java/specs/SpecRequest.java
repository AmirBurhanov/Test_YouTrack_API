package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.TestConfig;

public class SpecRequest {

  public static RequestSpecification getRequestSpec() {
    return new RequestSpecBuilder()
        .setBaseUri(TestConfig.getBaseUrl())
        .setBasePath(TestConfig.getBasePath())
        .addHeader("Authorization", "Bearer " + TestConfig.getApiToken())
        .setContentType(ContentType.JSON)
        .build();
  }

  public static RequestSpecification getUnauthorizedSpec() {
    return new RequestSpecBuilder()
        .setBaseUri(TestConfig.getBaseUrl())
        .setBasePath(TestConfig.getBasePath())
        .setContentType(ContentType.JSON)
        .build();
  }
}
