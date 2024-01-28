package com.myblog1.controller;


import com.myblog1.entity.Comment;
import com.myblog1.payload.CommentDto;
import com.myblog1.repository.CommentRepository;
import com.myblog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


     private CommentService commentService;
   

    public CommentController(CommentService commentService) {
       this.commentService = commentService;
    }

    @PostMapping
      public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto dto){

        CommentDto commentDto = commentService.saveComment(dto);

        return new ResponseEntity<>(commentDto , HttpStatus.CREATED);
    }



}
