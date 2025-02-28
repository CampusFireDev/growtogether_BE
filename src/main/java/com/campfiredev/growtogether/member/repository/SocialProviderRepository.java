package com.campfiredev.growtogether.member.repository;

import com.campfiredev.growtogether.member.entity.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SocialProviderRepository extends JpaRepository<SocialProvider, Long> {

    SocialProvider findByProviderId(String providerId);

}
