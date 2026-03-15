package specs;

import constants.TestConstants;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class SpecRespons {

  private static ResponseSpecification successResponseSpec;
  private static ResponseSpecification createdResponseSpec;
  private static ResponseSpecification badRequestResponseSpec;
  private static ResponseSpecification notFoundResponseSpec;

  public static ResponseSpecification success() {
    if (successResponseSpec == null) {
      successResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(TestConstants.STATUS_OK)
          .expectContentType(ContentType.JSON)
          .build();
    }
    return successResponseSpec;
  }

  public static ResponseSpecification created() {
    if (createdResponseSpec == null) {
      createdResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(TestConstants.STATUS_CREATED)
          .expectContentType(ContentType.JSON)
          .build();
    }
    return createdResponseSpec;
  }

  public static ResponseSpecification badRequest() {
    if (badRequestResponseSpec == null) {
      badRequestResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(TestConstants.STATUS_BAD_REQUEST)
          .build();
    }
    return badRequestResponseSpec;
  }

  public static ResponseSpecification notFound() {
    if (notFoundResponseSpec == null) {
      notFoundResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(TestConstants.STATUS_NOT_FOUND)
          .build();
    }
    return notFoundResponseSpec;
  }

  public static ResponseSpecification unauthorized() {
    return new ResponseSpecBuilder()
        .expectStatusCode(TestConstants.STATUS_UNAUTHORIZED)
        .build();
  }
}
