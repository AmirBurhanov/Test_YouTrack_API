package dto.request;

public static class ProjectRequest {
  private String id;

  public Project(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
