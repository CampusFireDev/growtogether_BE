package com.campfiredev.growtogether.member.repository;

import com.campfiredev.growtogether.member.entity.MemberSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSkillRepository extends JpaRepository<MemberSkillEntity, Long> {

}