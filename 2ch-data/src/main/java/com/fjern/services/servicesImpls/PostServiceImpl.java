package com.fjern.services.servicesImpls;

import com.fjern.entities.Post;
import com.fjern.entities.User;
import com.fjern.repositories.PostRepository;

import com.fjern.services.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Value("${page.size}")
    private int pageSize;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPage(int page) {
        return  postRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

//    public <T> List<T> findPage(int page, Class<T> type) {
//        return  postRepository.findAll(PageRequest.of(page,pageSize),type).getContent();
//    }
    @Override


    public Post addPost(Post post, Long parentId, User user){
        Post postParent=postRepository.findById(parentId).orElse(null);

        post.addParent(postParent);
        return postRepository.save(post);

    }

    @Override
    public Post getPost(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public Post findById(Long aLong) {
        return postRepository.findById(aLong).orElse(null);
    }

    @Override
    public Post save(Post object) {
        return postRepository.save(object);
    }

    @Override
    public void delete(Post object) {
        postRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        postRepository.deleteById(aLong);
    }

    @Override
    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }
}
