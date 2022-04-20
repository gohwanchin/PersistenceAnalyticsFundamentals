package sg.edu.nus.workshop26.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.workshop26.model.Post;
import sg.edu.nus.workshop26.repository.PostRepository;
import sg.edu.nus.workshop26.service.PostService;

@Controller
@RequestMapping
public class PostController {

    @Autowired
    private PostService postSvc;
    @Autowired
    private PostRepository postRepo;

    @GetMapping("/{postId}")
    public ModelAndView getPostById(@PathVariable Integer postId) {
        ModelAndView mvc = new ModelAndView();
        Optional<Post> opt = postRepo.getPostById(postId);
        mvc.addObject("post", opt.get());
        mvc.setViewName("post");
        return mvc;
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postUpload(@RequestParam MultipartFile image, @RequestParam String comment,
            @RequestParam String uploader) {
        ModelAndView mvc = new ModelAndView();

        Optional<Post> opt = postSvc.createPost(image, comment, uploader);
        if (opt.isEmpty()) {
            mvc.addObject("msg", "Could not add post");
            mvc.setViewName("error");
            return mvc;
        }

        Post p = opt.get();
        mvc.addObject("postId", p.getPostId());
        mvc.setViewName("result");
        return mvc;
    }

}
