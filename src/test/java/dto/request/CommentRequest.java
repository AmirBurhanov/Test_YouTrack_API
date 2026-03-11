package dto.request;

public class CommentRequest {
  private String text;

  public CommentRequest(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
