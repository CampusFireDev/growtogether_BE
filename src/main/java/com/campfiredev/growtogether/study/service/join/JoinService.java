package com.campfiredev.growtogether.study.service.join;

import com.campfiredev.growtogether.study.dto.join.StudyMemberListDto;

public interface JoinService {

  void join(Long UserEntity, Long studyId);

  void confirmJoin(Long StudyMemberEntity);

  void cancelJoin(Long StudyMemberEntity);

  StudyMemberListDto getPendingList(Long studyId);

  StudyMemberListDto getJoinList(Long studyId);
}
