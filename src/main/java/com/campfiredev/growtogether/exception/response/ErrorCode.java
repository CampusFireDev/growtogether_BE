package com.campfiredev.growtogether.exception.response;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  //예외 생길 때마다 이런 식으로 추가
  ALREADY_JOINED_STUDY("이미 참석 중인 스터디입니다.", BAD_REQUEST),
  NOT_A_STUDY_MEMBER("스터디 참가자가 아닙니다.", BAD_REQUEST),
  NOT_A_STUDY_LEADER("스터디 팀장이 아닙니다.", BAD_REQUEST),

  NOTICE_NOT_FOUND("존재하지 않는 공지사항입니다.",BAD_REQUEST),

  INVALID_INPUT_DATA("잘못된 입력 데이터입니다.", BAD_REQUEST),

  INTERNAL_SERVER_ERROR("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

  INVALID_SKILL("존재하지 않는 기술스택입니다.",BAD_REQUEST),

  START_DATE_PAST("시작 날짜는 현재 날짜 이후여야 합니다.",BAD_REQUEST),

  END_DATE_AFTER_START_DATE("종료 날짜는 시작 날짜 이후여야 합니다.",BAD_REQUEST),

  REVIEW_NOT_FOUND("존재하지 않는 부트캠프 후기 입니다.",BAD_REQUEST);

  private final String description;

  private final HttpStatus status;
}

