package com.campfiredev.growtogether.member.service;

import com.campfiredev.growtogether.bootcamp.entity.BootCampReview;
import com.campfiredev.growtogether.bootcamp.entity.ReviewLike;
import com.campfiredev.growtogether.bootcamp.repository.ReviewLikeRepository;
import com.campfiredev.growtogether.exception.custom.CustomException;
import com.campfiredev.growtogether.exception.response.ErrorCode;
import com.campfiredev.growtogether.member.dto.MyPageInfoDto;
import com.campfiredev.growtogether.member.dto.MyPageLikesDto;
import com.campfiredev.growtogether.member.dto.MyPageUpdateDto;
import com.campfiredev.growtogether.member.entity.MemberEntity;
import com.campfiredev.growtogether.member.repository.MemberRepository;
import com.campfiredev.growtogether.skill.repository.SkillRepository;
import com.campfiredev.growtogether.study.entity.Bookmark;
import com.campfiredev.growtogether.study.entity.Study;
import com.campfiredev.growtogether.study.repository.bookmark.BookmarkRepository;
import com.campfiredev.growtogether.study.repository.post.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final StudyRepository studyRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ReviewLikeRepository reviewLikeRepository;


    // 마이페이지 정보 조회 (프로필 + 찜한 부트캠프 후기 + 북마크한 스터디 게시글)
    public MyPageInfoDto getMyPageInfo(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 사용자의 기술 스택 조회
        List<String> skills = skillRepository.findSkillNamesByMemberId(memberId);

        // 찜한 부트캠프 후기 + 북마크한 스터디 게시글 리스트 조회
        List<MyPageLikesDto> likedPosts = getMyLikedPosts(memberId);

        return MyPageInfoDto.builder()
                .email(member.getEmail())
                .phone(member.getPhone())
                .nickName(member.getNickName())
                .profileImageUrl(member.getProfileImageUrl())
                .points(member.getPoints())
                .githubUrl(member.getGithubUrl())
                .skills(skills)
                .likedPostCount(likedPosts.size())
                .likedPosts(likedPosts)
                .build();
    }

    public List<MyPageLikesDto> getMyLikedPosts(Long memberId) {
        List<MyPageLikesDto> likedPosts = new ArrayList<>();

        // 1. 부트캠프 후기 좋아요 리스트
        List<ReviewLike> bootcampLikes = reviewLikeRepository.findByMemberMemberId(memberId);
        for (ReviewLike like : bootcampLikes) {
            if (like != null && like.getBootCampReview() != null) {
                BootCampReview review = like.getBootCampReview();

                // 스킬 이름 목록 추출
                List<String> skillNames = review.getBootCampSkills().stream()
                        .map(bs -> bs.getSkill().getSkillName())
                        .toList();

                likedPosts.add(MyPageLikesDto.builder()
                        .postId(review.getBootCampId())
                        .title(review.getTitle())
                        .type("BootCamp")
                        .bootcampSkillNames(skillNames)
                        .programCourse(review.getProgramCourse().name())
                        .build());
            }
        }
        // 2. 스터디 북마크 리스트
        List<Bookmark> bookmarks = bookmarkRepository.findByMember_MemberId(memberId);
        for (Bookmark bookmark : bookmarks) {
            Study study = bookmark.getStudy();
            if (study != null) {
                List<String> skillNames = study.getSkillStudies().stream()
                        .map(s -> s.getSkill().getSkillName())
                        .toList();

                likedPosts.add(MyPageLikesDto.builder()
                        .postId(study.getStudyId())
                        .title(study.getTitle())
                        .type(study.getType())
                        .people(study.getMaxParticipant())
                        .skillName(skillNames)
                        .status(study.getStudyStatus().name())
                        .build());
            }
        }

        return likedPosts;
    }


    @Transactional
    public MyPageUpdateDto updateMyPageInfo(Long memberId, MyPageUpdateDto myPageInfoDto) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        member.setNickName(myPageInfoDto.getNickName());
        member.setProfileImageUrl(myPageInfoDto.getProfileImgUrl());
        member.setGithubUrl(myPageInfoDto.getGithubUrl());
        member.setPhone(myPageInfoDto.getPhone());

//        member.getUserSkills().clear();
//        member.getUserSkills().addAll(myPageInfoDto.getSkills());

        return myPageInfoDto;
    }

}
