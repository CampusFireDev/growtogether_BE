package com.campfiredev.growtogether.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record UserInfoResponse(
	@JsonProperty("id")
	String id,
	@JsonProperty("connected_at")
	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
		timezone = "Asia/Seoul"
	)
	LocalDateTime connectedAt
) {
}
