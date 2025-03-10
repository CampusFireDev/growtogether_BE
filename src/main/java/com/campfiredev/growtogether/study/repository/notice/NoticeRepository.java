package com.campfiredev.growtogether.study.repository.notice;

import com.campfiredev.growtogether.study.entity.notice.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

  Page<NoticeEntity> findByStudy_StudyId(Long studyId, Pageable pageable);

}
