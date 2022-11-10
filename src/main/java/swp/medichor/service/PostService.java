package swp.medichor.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Post;
import swp.medichor.model.User;
import swp.medichor.model.request.PostRequest;
import swp.medichor.model.response.PostResponse;
import swp.medichor.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }

    public Optional<PostResponse> getById(int id) {
        return postRepository.findById(id)
                .map(post -> new PostResponse(post));
    }

    public int addPost(PostRequest postInfo, User user) {
        Post post = Post.builder()
                .postingTime(new Timestamp(System.currentTimeMillis()))
                .title(postInfo.getTitle())
                .author(postInfo.getAuthor())
                .content(postInfo.getContent())
                .images(postInfo.getImages())
                .category(postInfo.getCategory())
                .status(true)
                .postingUser(user)
                .build();
        return postRepository.save(post).getId();
    }

    public void updatePost(int id, PostRequest postInfo) {
        postRepository.findById(id)
                .ifPresentOrElse(post -> {
                    post.setTitle(postInfo.getTitle());
                    post.setAuthor(postInfo.getAuthor());
                    post.setContent(postInfo.getContent());
                    post.setImages(postInfo.getImages());
                    post.setCategory(postInfo.getCategory());
                    postRepository.save(post);
                }, () -> {
                    throw new RuntimeException("ID của bài đăng không tồn tại");
                });
    }

    public void hide(int id) {
        postRepository.findById(id)
                .ifPresentOrElse(post -> {
                    post.setStatus(false);
                    postRepository.save(post);
                }, () -> {
                    throw new RuntimeException("ID của bài đăng không tồn tại");
                });
    }

    public void unhide(int id) {
        postRepository.findById(id)
                .ifPresentOrElse(post -> {
                    post.setStatus(true);
                    postRepository.save(post);
                }, () -> {
                    throw new RuntimeException("ID của bài đăng không tồn tại");
                });
    }
}
