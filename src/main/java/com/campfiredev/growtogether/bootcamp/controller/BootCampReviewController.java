package com.campfiredev.growtogether.bootcamp.controller;

import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewCreateDto;
import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewRequest;
import com.campfiredev.growtogether.bootcamp.dto.BootCampReviewUpdateDto;
import com.campfiredev.growtogether.bootcamp.service.BootCampReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor
public class BootCampReviewController {

    private final BootCampReviewService reviewService;

    /**
     * 부트캠프 리뷰 등록
     * @param request
     * @return 성공메세지
     */
    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody BootCampReviewCreateDto.Request request) {


        reviewService.createReview(request);

        return ResponseEntity.ok("부트캠프 리뷰 등록이 완료되었습니다.");
    }

    /**
     * 부트캠프 리뷰 수정
     * @param bootCampReviewId
     * @param request
     * @return
     */
    @PutMapping("/{bootCampReviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long bootCampReviewId,@Valid @RequestBody BootCampReviewUpdateDto.Request request) {


        reviewService.updateReview(bootCampReviewId , request);

        return ResponseEntity.ok("부트캠프 리뷰 수정이 완료되었습니다.");
    }

    /**
     * 부트캠프 리뷰 삭제
     * @param bootCampReviewId
     * @return
     */
    @DeleteMapping("/{bootCampReviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long bootCampReviewId) {

        reviewService.deleteReview(bootCampReviewId);

        return ResponseEntity.ok("부트캠프 리뷰 삭제가 완료되었습니다.");
    }

}
