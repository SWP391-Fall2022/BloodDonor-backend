package swp.medichor.model.request;

import lombok.Getter;

@Getter
public class PostRequest {

    private String title;
    private String author;
    private String content;
    private String images;
    private Integer category;
}
