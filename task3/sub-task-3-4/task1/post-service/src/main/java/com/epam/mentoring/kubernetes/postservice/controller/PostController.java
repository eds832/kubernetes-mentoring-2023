package com.epam.mentoring.kubernetes.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mentoring.kubernetes.postservice.service.PostService;
import com.epam.mentoring.kubernetes.postservice.controller.requestfailure.PostRequestFailure;
import com.epam.mentoring.kubernetes.postservice.domain.Post;
import com.epam.mentoring.kubernetes.postservice.dto.request.PostRequestDto;
import com.epam.mentoring.kubernetes.postservice.dto.response.PostResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Eduard_Sardyka
 */
@RestController
@RequestMapping(path = "/posts")
@Api(value = "post-service")
public class PostController {

    private PostService postService;

    private ConversionService conversionService;

    @Autowired
    public PostController(PostService postService, ConversionService conversionService) {
        this.postService = postService;
        this.conversionService = conversionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = PostResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE,
        value = "Data of post from specified user to be created.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = PostResponseDto.class),
        @ApiResponse(code = 400, message = "Validation error or request body is an invalid."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        if (postRequestDto.getAuthorId() != null && postRequestDto.getText() != null
            && !postRequestDto.getText()
            .isBlank() && postRequestDto.getTopic() != null && !postRequestDto.getTopic().isBlank()) {
            postRequestDto.setId(null);
            Post post = conversionService.convert(postRequestDto, Post.class);
            post = postService.createPost(post);
            if (post != null) {
                return convertPostToResponseEntity(post);
            }
        }
        return ResponseEntity.badRequest()
            .body(new PostRequestFailure(null, postRequestDto.getAuthorId(), postRequestDto.getTopic(),
                postRequestDto.getText(), null,
                "Validation error or request body is an invalid."));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = PostResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Update post’s data.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = PostResponseDto.class),
        @ApiResponse(code = 400, message = "Validation error or request body is an invalid."),
        @ApiResponse(code = 404, message = "Post doesn’t exist with given id."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<PostResponseDto> updatePost(
        @ApiParam("Id of user to update.") @PathVariable(value = "id") Long id,
        @RequestBody PostRequestDto postRequestDto) {
        postRequestDto.setId(id);
        if (postRequestDto.getId() != null && postRequestDto.getText() != null && !postRequestDto.getText().isBlank()
            && postRequestDto.getTopic() != null && !postRequestDto.getTopic().isBlank()) {
            Post post = conversionService.convert(postRequestDto, Post.class);
            post = postService.updatePost(post);
            if (post != null) {
                return convertPostToResponseEntity(post);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest()
                .body(new PostRequestFailure(postRequestDto.getId(), postRequestDto.getAuthorId(),
                    postRequestDto.getTopic(), postRequestDto.getText(), null,
                    "Validation error or request body is an invalid."));
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = PostResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Delete a post by id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = PostResponseDto.class),
        @ApiResponse(code = 404, message = "Not Found."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<PostResponseDto> deletePost(
        @ApiParam("Id of the Post to be deleted. Cannot be empty.") @PathVariable(value = "id") Long id) {
        if (id != null) {
            Post post = postService.deletePost(id);
            if (post != null) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(response = PostResponseDto.class, produces = MediaType.APPLICATION_JSON_VALUE, value = "Gets post’s data by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = PostResponseDto.class),
        @ApiResponse(code = 404, message = "Post doesn’t exist with given id."),
        @ApiResponse(code = 500, message = "Internal server error occurred.") })
    public @ResponseBody ResponseEntity<PostResponseDto> getUser(
        @ApiParam("Id of post to delete.") @PathVariable(value = "id") Long id) {
        if (id != null) {
            Post post = postService.getPost(id);
            if (post != null) {
                return convertPostToResponseEntity(post);
            }
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<PostResponseDto> convertPostToResponseEntity(Post post) {
        PostResponseDto dto = conversionService.convert(post, PostResponseDto.class);
        return ResponseEntity.ok(dto);
    }

}