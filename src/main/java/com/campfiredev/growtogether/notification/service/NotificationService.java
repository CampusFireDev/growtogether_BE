package com.campfiredev.growtogether.notification.service;

import com.campfiredev.growtogether.member.entity.MemberEntity;
import com.campfiredev.growtogether.notification.entity.Notification;
import com.campfiredev.growtogether.notification.type.NotiType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface NotificationService {

    //sse 구독
    SseEmitter subscribe(Long userId);

    //알림 전송
    void sendNotification(MemberEntity member, String content, NotiType type);

    // 읽지 않는 알림 조회
    List<Notification> getUnReadNotifiactions(MemberEntity member);

    //알림 읽음 처리
    void markNotification(Long notiId);
}
