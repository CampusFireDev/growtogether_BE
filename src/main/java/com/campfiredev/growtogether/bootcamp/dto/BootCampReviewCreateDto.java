package com.campfiredev.growtogether.bootcamp.dto;

import com.campfiredev.growtogether.bootcamp.entity.BootCampReview;
import com.campfiredev.growtogether.bootcamp.type.ProgramCourse;
import com.campfiredev.growtogether.member.entity.MemberEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BootCampReviewCreateDto {


    private Long memberId;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private String imageUrl;

    @NotBlank(message = "부트캠프 이름은 필수 입력값입니다.")
    private String bootCampName;

    @NotNull(message = "학습난이도는 필수 입력값입니다.")
    private Integer learningLevel;

    @NotNull(message = "취업 지원 만족도는 필수 입력값입니다.")
    private Integer assistantSatisfaction;

    @NotNull(message = "강의 만족도는 필수 입력값입니다.")
    private Integer programSatisfaction;

    @NotNull(message = "프로그램 과정은 필수 입력값 입니다.")
    private String programCourse;

    @NotNull(message = "부트캠프 시작 날짜는 필수 입력값입니다.")
    private LocalDate bootCampStartDate;

    @NotNull(message = "부트캠프 종료날짜는 필수 입력값입니다.")
    private LocalDate bootCampEndDate;

    private List<String> skillNames;


     // BootCampReviewCreateDto → BootCampReview 엔티티 변환
    public BootCampReview toEntity(MemberEntity member) {
        return BootCampReview.builder()
                .member(member)
                .title(this.title)
                .content(this.content)
                .imageUrl(this.imageUrl)
                .bootCampName(this.bootCampName)
                .learningLevel(this.learningLevel)
                .assistantSatisfaction(this.assistantSatisfaction)
                .programSatisfaction(this.programSatisfaction)
                .programCourse(getProgramCourseEnum())
                .bootCampStartDate(this.bootCampStartDate)
                .bootCampEndDate(this.bootCampEndDate)
                .viewCount(0L) // 기본값 설정
                .likeCount(0)  // 기본값 설정
                .build();
    }

    // BootCampReview -> BootCampReviewCreateDTO
    public static BootCampReviewCreateDto fromEntity(BootCampReview review){

        List<String> skillNames = review.getBootCampSkills().stream()
                .map(skill -> skill.getSkill().getSkillName())
                .toList();

        return BootCampReviewCreateDto.builder()
                .memberId(review.getMember().getMemberId())
                .title(review.getTitle())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .bootCampName(review.getBootCampName())
                .learningLevel(review.getLearningLevel())
                .assistantSatisfaction(review.getAssistantSatisfaction())
                .programSatisfaction(review.getProgramSatisfaction())
                .programCourse(review.getProgramCourse().name())
                .bootCampStartDate(review.getBootCampStartDate())
                .bootCampEndDate(review.getBootCampEndDate())
                .skillNames(skillNames)
                .build();
    }

    public ProgramCourse getProgramCourseEnum(){
        return ProgramCourse.valueOf(programCourse.toUpperCase());
    }
}