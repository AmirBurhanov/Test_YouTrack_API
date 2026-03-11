package dto.request;

public class CreateIssueRequest {

  private String summary;
  private String description;
  private Project project;

  public CreateIssueRequest(String summary, String description, String projectId) {
    this.summary = summary;
    this.description = description;
    this.project = new Project(projectId);

  }

  public CreateIssueRequest(String summary, String description) {
    this.summary = summary;
    this.description = description;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public Project getProject() {
    return project;
  }
}
