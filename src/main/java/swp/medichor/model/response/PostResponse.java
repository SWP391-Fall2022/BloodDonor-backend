package swp.medichor.model.response;

import java.sql.Timestamp;
import lombok.Getter;
import swp.medichor.model.Post;

@Getter
public class PostResponse {

    private Integer id;
    private Timestamp postingTime;
    private String title;
    private String author;
    private String content;
    private String images;
    private Integer category;
    private Boolean status;
    private Integer userId;

    public PostResponse(Post post) {
        id = post.getId();
        postingTime = post.getPostingTime();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent();
        images = post.getImages();
        category = post.getCategory();
        status = post.getStatus();
        userId = post.getPostingUser().getId();
    }
}
