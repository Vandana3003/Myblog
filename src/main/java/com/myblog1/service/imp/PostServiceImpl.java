package com.myblog1.service.imp;

import com.myblog1.entity.Post;

import com.myblog1.exception.ResourceNotFound;
import com.myblog1.payload.PostDto;
import com.myblog1.payload.PostResponse;
import com.myblog1.repository.PostRepository;
import com.myblog1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class PostServiceImpl  implements PostService {

       private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         post.setDescription(postDto.getDescription());

        Post updatedpost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        
             
        
        return dto;
    }

    @Override
    public void deleteById(long id) {

        Post post= postRepository.findById(id).orElseThrow(

                       ()-> new ResourceNotFound("resource not found for id :" +id)
               );


        postRepository.deleteById(id);



    }

    @Override
    public PostDto getPostById(long id) {

        Post post= postRepository.findById(id).orElseThrow(

                ()-> new ResourceNotFound("resource not found for id :" +id)
        );



        return mapToDto(post);
    }

    @Override
    public PostResponse findAllPost(int pageNo,int pageSize,String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);

        Page<Post> all = postRepository.findAll(pageable);

        List<Post> post = all.getContent();

        List<PostDto> dtos = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(dtos);
        postResponse.setPageNo(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalPage(all.getTotalPages());
        postResponse.setTotalElement((int) all.getTotalElements());
        postResponse.setLast(all.isLast());


        return postResponse;
    }

    PostDto mapToDto(Post post){

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;

    }



}
