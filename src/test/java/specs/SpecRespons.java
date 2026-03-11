package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class SpecResponse {

  private static ResponseSpecification successResponseSpec;
  private static ResponseSpecification createdResponseSpec;
  private static ResponseSpecification badRequestResponseSpec;
  private static ResponseSpecification notFoundResponseSpec;

  public static ResponseSpecification success() {
    if (successResponseSpec == null) {
      successResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(200)
          .expectContentType(ContentType.JSON)
          .build();
    }
    return successResponseSpec;
  }

  public static ResponseSpecification created() {
    if (createdResponseSpec == null) {
      createdResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(201)
          .expectContentType(ContentType.JSON)
          .build();
    }
    return createdResponseSpec;
  }

  public static ResponseSpecification badRequest() {
    if (badRequestResponseSpec == null) {
      badRequestResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(400)
          .build();
    }
    return badRequestResponseSpec;
  }

  public static ResponseSpecification notFound() {
    if (notFoundResponseSpec == null) {
      notFoundResponseSpec = new ResponseSpecBuilder()
          .expectStatusCode(404)
          .build();
    }
    return notFoundResponseSpec;
  }

  public static ResponseSpecification status(int statusCode) {
    return new ResponseSpecBuilder()
        .expectStatusCode(statusCode)
        .build();
  }
}
