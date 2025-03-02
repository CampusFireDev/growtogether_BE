package com.campfiredev.growtogether.study.repository.join;

import com.campfiredev.growtogether.member.entity.MemberEntity;
import com.campfiredev.growtogether.study.entity.Study;
import com.campfiredev.growtogether.study.entity.join.StudyMemberEntity;
import com.campfiredev.growtogether.study.type.StudyMemberType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoinRepository extends JpaRepository<StudyMemberEntity, Long> {

  Optional<StudyMemberEntity> findByMemberAndStudy(MemberEntity member, Study study);

  @Query("SELECT sm FROM StudyMemberEntity sm " +
      "JOIN FETCH sm.study s " +
      "JOIN FETCH sm.member u " +
      "WHERE sm.id = :StudyMemberEntity")
  Optional<StudyMemberEntity> findWithStudyAndMemberById(
      @Param("StudyMemberEntity") Long StudyMemberEntity);

  @Query("SELECT sm.member.memberId FROM StudyMemberEntity sm WHERE sm.id = :StudyMemberEntity")
  Long getUserEntityByStudyMemberEntity(@Param("StudyMemberEntity") Long StudyMemberEntity);

  @Query("SELECT sm FROM StudyMemberEntity sm " +
      "JOIN FETCH sm.member " +
      "WHERE sm.study.studyId = :studyId and sm.status IN :statuses")
  List<StudyMemberEntity> findByStudyWithMembersInStatus(@Param("studyId") Long studyId,
      @Param("statuses") List<StudyMemberType> statuses);
}

