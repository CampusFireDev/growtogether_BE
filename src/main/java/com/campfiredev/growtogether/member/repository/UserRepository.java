package com.campfiredev.growtogether.member.repository;

import com.campfiredev.growtogether.member.entity.WebtyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<WebtyUser, Long> {

    WebtyUser findByUserId(Long userId);

}
