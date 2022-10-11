package swp.medichor.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import swp.medichor.model.User;
import swp.medichor.model.request.PostRequest;
import swp.medichor.model.response.PostResponse;
import swp.medichor.model.response.Response;
import swp.medichor.service.PostService;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public Response getAllPosts() {
        return new Response(200, true, postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public Response getPost(@PathVariable int id) {
        Optional<PostResponse> post = postService.getById(id);
        if (post.isPresent()) {
            return new Response(200, true, post.get());
        }
        return new Response(404, false, null);
    }

    @PutMapping("/{id}")
    public Response updatePost(@PathVariable int id, @RequestBody PostRequest postInfo) {
        try {
            postService.updatePost(id, postInfo);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @PostMapping
    public Response createPost(@RequestBody PostRequest postInfo, @RequestAttribute User user) {
        int newPostId = postService.addPost(postInfo, user);
        return new Response(200, true, newPostId);
    }

    @PutMapping("/{id}/hide")
    public Response hidePost(@PathVariable int id) {
        try {
            postService.hide(id);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @PutMapping("/{id}/unhide")
    public Response unhidePost(@PathVariable int id) {
        try {
            postService.unhide(id);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }
}
