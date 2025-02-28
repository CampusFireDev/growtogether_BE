package com.campfiredev.growtogether.member.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Getter
@Component
@ConfigurationProperties("spring.security.oauth2.client")
public class OAuthProperties {

	private String clientId;
	private String clientSecret;
	private String clientAuthenticationMethod;
	private String redirectUri;
	private String authorizationGrantType;
	private String clientName;

	private String tokenUri;
	private String userInfoUri;
	private String authorizationUri;
	private String userNameAttribute;

}
