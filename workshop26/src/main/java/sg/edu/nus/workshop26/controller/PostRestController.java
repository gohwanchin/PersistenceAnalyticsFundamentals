package sg.edu.nus.workshop26.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.workshop26.model.Post;
import sg.edu.nus.workshop26.repository.PostRepository;

@RestController
public class PostRestController {

    @Autowired
    private PostRepository postRepo;

    // To load image directly from database
    // Not necessary as currently using S3 url
    /*
     * @GetMapping("/{postId}/image")
     * public ResponseEntity<byte[]> getPostImage(@PathVariable Integer postId){
     * Optional<Post> opt = postRepo.getPostById(postId);
     * if(opt.isEmpty())
     * return ResponseEntity.notFound().build();
     * Post post = opt.get();
     * HttpHeaders headers = new HttpHeaders();
     * headers.add("Content-Type", post.getImageType());
     * return ResponseEntity.ok().headers(headers).body(post.getImage());
     * }
     */
}
