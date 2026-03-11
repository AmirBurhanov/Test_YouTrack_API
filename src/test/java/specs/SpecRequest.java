package specs;

import utils.TestConfig;
import io.restassured.builder.RequestSpecBuilder;

public class SpecRequest {

  public static RequestSpecification getRequestSpec() {
    if (requestSpec == null) {
      requestSpec = new RequestSpecBuilder()
          .setBaseUri(TestConfig.getBaseUrl())
          .setBasePath(TestConfig.getBasePath())
          .addHeader("Authorization", "Bearer " + TestConfig.getApiToken())
          .setContentType(ContentType.JSON)
          .build();
    }
    return requestSpec;
  }
}
