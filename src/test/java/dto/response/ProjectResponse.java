package dto.response;

public class ProjectResponse {
  private String id;
  private String name;
  private String shortName;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
}
