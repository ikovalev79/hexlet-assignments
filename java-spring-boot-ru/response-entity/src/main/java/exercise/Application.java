package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts") // Список постов
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        var result = posts.stream().limit(limit).toList();
        
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(result);
    }
    
    @PostMapping("/posts") // Создание поста
    public ResponseEntity<Post> create(@RequestBody Post post) {
    	posts.add(post);
        
    	return ResponseEntity
    			.status(HttpStatus.CREATED)
    			.body(post);
    }

    @GetMapping("/posts/{id}") // Вывод поста
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        
        if (post.isPresent()) {
        	return ResponseEntity.ok()
        			.body(post.get());
        }
        else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
        			.body(null);
        }
    }

    @PutMapping("/posts/{id}") // Обновление поста
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            
            post.setId(data.getId());
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
            
            return ResponseEntity.ok().body(post);
        }
        else {
        	return ResponseEntity.status(HttpStatus.NO_CONTENT)
        			.body(null);
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
