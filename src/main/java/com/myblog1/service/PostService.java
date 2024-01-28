package com.myblog1.service;

import com.myblog1.payload.PostDto;
import com.myblog1.payload.PostResponse;

import java.util.List;

public interface PostService {

  public  PostDto createPost(PostDto postDto);

    void deleteById(long id);

    PostDto getPostById(long id);

  PostResponse findAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
