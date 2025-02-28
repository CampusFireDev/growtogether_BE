package com.campfiredev.growtogether.member.oauth2;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoMemberInfo implements OAuth2MemberInfo {
    
    private Map<String, Object> attributes;

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }
    
    @Override
    public String getProvider() {
        return "kakao";
    }

}