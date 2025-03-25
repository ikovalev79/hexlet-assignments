package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
	
	private List<Post> posts = Data.getPosts();
    
	@GetMapping("/users/{id}/posts") // Список постов указанного юзера
    public List<Post> userPosts(@PathVariable String id) {
		var result = posts
				.stream()
				.filter(p -> String.valueOf(p.getUserId()).equals(id))
				.toList();
    
		return result;
	}
	
    @PostMapping("/users/{id}/posts") // Создание поста для указанного юзера
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUserPost(@PathVariable String id, @RequestBody Post post) {
    	Post newUserPost = new Post();
    	newUserPost.setUserId(Integer.valueOf(id));
    	newUserPost.setSlug(post.getSlug());
    	newUserPost.setTitle(post.getTitle());
    	newUserPost.setBody(post.getBody());
    	
    	posts.add(newUserPost);
    	
    	return newUserPost;
    }
}
// END