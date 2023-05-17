package com.epam.mentoring.kubernetes.postservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.epam.mentoring.kubernetes.postservice.domain.Post;

/**
 * @author Eduard_Sardyka
 */
@RepositoryRestResource(exported = false)
public interface PostRepository extends CrudRepository<Post, Long> {

}