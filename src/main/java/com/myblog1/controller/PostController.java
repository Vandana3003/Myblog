package com.myblog1.controller;

import com.myblog1.payload.PostDto;
import com.myblog1.payload.PostResponse;
import com.myblog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);


    }
           @DeleteMapping("/{id}")
          public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
                  postService.deleteById(id);
              return new ResponseEntity<>("record deleted by id :"+id ,HttpStatus.OK);

          }


    @GetMapping ("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto ,HttpStatus.OK);

    }
     @GetMapping
    public PostResponse findAllPost(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
                                    @RequestParam(value="pageSize",defaultValue = "10",required = false)int pageSize,
                                    @RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
                                    @RequestParam(value="sortDir",defaultValue = "acs",required = false)String sortDir

                                     ){

         PostResponse postResponse = postService.findAllPost(pageNo, pageSize, sortBy, sortDir);
         return postResponse;
    }






}
