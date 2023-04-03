package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Comment;
import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMessage(String message);

    List<Comment> findByMessageContains(String message);

    Comment findByOwner(User owner);

    Comment findByProduct(Product product);
}
