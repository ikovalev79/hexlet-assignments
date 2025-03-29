package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
import java.util.ArrayList;

@RestController
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	private PostDTO toPostDTO(Post post) {
		var postDto = new PostDTO();
		
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setBody(post.getBody());
		postDto.setComments(new ArrayList<CommentDTO>());
		
		var commentList = commentRepository.findByPostId(post.getId());
		
		for (var comment : commentList) {
			var commentDto = new CommentDTO();
			
			commentDto.setId(comment.getId());
			commentDto.setBody(comment.getBody());
			
			postDto.getComments().add(commentDto);
		}
		
		return postDto;
	}
	
    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
    	var post = postRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    	
    	return toPostDTO(post);
    }
    
    @GetMapping("")
    public List<PostDTO> index() {
    	var posts = postRepository.findAll();
        var result = posts.stream().map(this::toPostDTO).toList();
    	return result;
    }
}
// END