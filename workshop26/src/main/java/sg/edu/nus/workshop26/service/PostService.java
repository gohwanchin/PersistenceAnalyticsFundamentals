package sg.edu.nus.workshop26.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.workshop26.model.Post;
import sg.edu.nus.workshop26.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private AmazonS3 s3;

    public Optional<Post> createPost(MultipartFile image, String comment, String uploader) {
        String imageName = image.getOriginalFilename();
        Long imageSize = image.getSize();
        String imageType = image.getContentType();
        String url = "";

        String uuid = UUID.randomUUID().toString().substring(0, 8);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageType);
        metadata.addUserMetadata("imageSize", imageSize.toString());
        metadata.addUserMetadata("imageName", imageName);
        metadata.addUserMetadata("comment", comment);
        metadata.addUserMetadata("uploader", uploader);
        metadata.addUserMetadata("timestamp", Long.toString(System.currentTimeMillis()));
        try {
            PutObjectRequest putReq = new PutObjectRequest("arcane",
                    "%s/images/%s".formatted(uploader, uuid), image.getInputStream(), metadata);
            putReq.setCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putReq);
            url = s3.getUrl("arcane", "%s/images/%s".formatted(uploader, uuid)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Post p = new Post();
        p.setComment(comment);
        p.setUploader(uploader);
        p.setImage(url);
        p.setImageType(imageType);

        Integer postId = null;
        try {
            postId = postRepo.insertPost(p);
        } catch (Exception e) {
            return Optional.empty();
        }
        p.setPostId(postId);
        return Optional.of(p);
    }
}
