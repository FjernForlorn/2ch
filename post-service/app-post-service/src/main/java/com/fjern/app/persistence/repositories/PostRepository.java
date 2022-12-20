package com.fjern.app.persistence.repositories;

import com.fjern.app.persistence.entities.Post;
import com.fjern.common.interfaces.ByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, ByNameApi<Post> {

//    <T>Page<T> findAll(Pageable pageable, Class<T> type);


}
