package specs;

import java.io.ObjectInputFilter.Config;

import io.restassured.builder.RequestSpecBuilder;

public class SpecRequest {
  private String baseUrl = Config.getProperty("base.url");

  public static RequestSpecification getRequestSpec() {
    if (requestSpec == null) {
      requestSpec = new RequestSpecBuilder()
          .setBaseUri(properties.getProperty("base.url"))
          .setBasePath("/api")
          .addHeader("Authorization", "Bearer " + properties.getProperty("api.token"))
          .setContentType("application/json")
          .build();

      Reporter.log(
          "RequestSpecification создана для URL: " + properties.getProperty("base.url"), true);
    }
    return requestSpec;
  }

  public static SpecRequest getBaseRequestSpec() {
    return new RequestSpecBuilder()
        .setBaseUri(baseUrl);
  }

}
