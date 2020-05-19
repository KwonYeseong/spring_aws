package com.yeseong.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* Posts 클래스로 DB에 접근하게 해줄 클래스
* JpaRepository상속만으로 CRUD 메소드가 자동으로 생성됨
* Entity 클래스와 항상 함께 있어야함.
* */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
