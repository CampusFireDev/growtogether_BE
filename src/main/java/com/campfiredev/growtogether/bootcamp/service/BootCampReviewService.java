package com.campfiredev.growtogether.bootcamp.service;

import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewCreateDto;
import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewRequest;
import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewUpdateDto;
import com.campfiredev.growtogether.bootcamp.entity.BootCampReview;
import com.campfiredev.growtogether.bootcamp.repository.BootCampReviewRepository;
import com.campfiredev.growtogether.exception.custom.CustomException;
import com.campfiredev.growtogether.exception.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BootCampReviewService {

    private final BootCampReviewRepository bootCampReviewRepository;

    //후기 등록
    @Transactional
    public void createReview(BootCampReviewCreateDto.Request request) {

        bootCampReviewRepository.save(request.toEntity());

    }

    //후기 수정
    @Transactional
    public void updateReview(Long bootCampReviewId,BootCampReviewUpdateDto.Request request) {

        BootCampReview review = bootCampReviewRepository.findById(bootCampReviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        // 자기 자신 이외에 사람은 수정을 할 수 없게 예외처리 예정

        request.updateEntity(review);


    }

    //후기 삭제
    @Transactional
    public void deleteReview(Long bootCampReviewId){
        BootCampReview review =  bootCampReviewRepository.findById(bootCampReviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        bootCampReviewRepository.delete(review);
    }


}
