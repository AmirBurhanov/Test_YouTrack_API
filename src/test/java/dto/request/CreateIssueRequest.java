package dto.request;

public class CreateIssueRequest {

  private String summary;
  private String description;
  private ProjectRequest project;

  public CreateIssueRequest(String summary, String description, String projectId) {
    this.summary = summary;
    this.description = description;
    this.project = new ProjectRequest(projectId);

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

  public ProjectRequest getProject() {
    return project;
  }
}
