package com.campfiredev.growtogether.bootcamp.dto;

import com.campfiredev.growtogether.bootcamp.entity.BootCampReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BootCampReviewResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String bootCampName;
        private LocalDate bootCampStartDate;
        private LocalDate bootCampEndDate;
        private int learningLevel;
        private int assistantSatisfaction;
        private int programSatisfaction;
        private int likeCount;
        private int viewCount;
        private int commentCount;
        private List<String> skills;

        public static Response fromEntity(BootCampReview review) {

            return Response.builder()
                    .id(review.getBootCampReviewId())
                    .title(review.getTitle())
                    .content(review.getContent())
                    .bootCampName(review.getBootCampName())
                    .bootCampStartDate(review.getBootCampStartDate())
                    .bootCampEndDate(review.getBootCampEndDate())
                    .assistantSatisfaction(review.getAssistantSatisfaction())
                    .programSatisfaction(review.getProgramSatisfaction())
                    .likeCount(review.getLikeCount())
                    .viewCount(review.getLikeCount())
                    .commentCount(review.getLikeCount())
                    //.skills....
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PageResponse {
        private List<Response> reviews;
        private int totalPages;
        private int curPage;

        public static PageResponse fromEntityPage(Page<BootCampReview> bootCampReviews){

            return PageResponse.builder()
                    .reviews(bootCampReviews.getContent().stream()
                            .map(Response::fromEntity)
                            .collect(Collectors.toList()))
                    .totalPages(bootCampReviews.getTotalPages())
                    .curPage(bootCampReviews.getNumber())
                    .build();
        }
    }
}
