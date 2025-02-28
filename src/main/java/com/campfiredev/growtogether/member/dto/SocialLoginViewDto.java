package com.campfiredev.growtogether.member.dto;

public record SocialLoginViewDto(
	Long id,
	String accessToken,
	String refreshToken,
	boolean isInitialized
) {
}
