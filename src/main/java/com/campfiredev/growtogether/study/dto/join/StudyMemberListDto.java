package com.campfiredev.growtogether.study.dto.join;

import com.campfiredev.growtogether.study.entity.join.StudyMemberEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyMemberListDto {

  private List<Info> pending;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Info {
    private Long memberId;

    private String nickname;

    private Long StudyMemberEntity;
  }

  public static StudyMemberListDto fromEntity(List<StudyMemberEntity> list){

    return new StudyMemberListDto(list.stream()
        .map(a -> Info.builder()
            .memberId(a.getMember().getMemberId())
            .nickname(a.getMember().getNickName())
            .StudyMemberEntity(a.getId())
            .build())
        .collect(Collectors.toList()));
  }
}
