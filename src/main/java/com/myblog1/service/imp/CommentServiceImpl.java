package com.myblog1.service.imp;

import com.myblog1.entity.Comment;
import com.myblog1.entity.Post;
import com.myblog1.exception.ResourceNotFound;
import com.myblog1.payload.CommentDto;
import com.myblog1.repository.CommentRepository;
import com.myblog1.repository.PostRepository;
import com.myblog1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl  implements CommentService {

   @Autowired
   private  PostRepository postRepo;
   private CommentRepository commentRepo;



    @Override
    public CommentDto saveComment(CommentDto dto) {

        Post post = postRepo.findById(dto.getPostId()).orElseThrow(
                () -> new ResourceNotFound("resource not found with id ")

        );


        Comment comments = new Comment();
        comments.setBody(dto.getBody());
        comments.setEmail(dto.getEmail());
        comments.setId(dto.getId());
        comments.setName(dto.getName());
        comments.setPost(post);

        Comment save = commentRepo.save(comments);

        CommentDto Dto = new CommentDto();
        Dto.setBody(comments.getBody());
        Dto.setEmail(comments.getEmail());
        Dto.setId(comments.getId());
        Dto.setName(comments.getName());

        return Dto;
    }
}
