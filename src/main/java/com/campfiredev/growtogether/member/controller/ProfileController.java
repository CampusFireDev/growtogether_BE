package com.campfiredev.growtogether.member.controller;

import com.campfiredev.growtogether.member.service.MemberService;
import com.campfiredev.growtogether.member.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/member/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final S3Service s3Service;
    private final MemberService memberService;

    //  이미지 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3Service.uploadFile(file);
        return ResponseEntity.ok(Map.of("message", "이미지 업로드 성공", "imageUrl", fileUrl));
    }
    // 프로필 이미지 URL 조회 API
    @GetMapping("/image/{fileKey}")
    public ResponseEntity<?> getProfileImageUrl(@PathVariable String fileKey) {
        String fileUrl = s3Service.getFileUrl(fileKey);
        return ResponseEntity.ok(Map.of("profileImageUrl", fileUrl));
    }
    // 사용자 ID로 프로필 이미지 조회
    @GetMapping("/image/{memberId}")
    public ResponseEntity<?> getProfileImageBymemberId(@PathVariable Long memberId) {
        String fileUrl = memberService.getProfileImageUrl(memberId);
        return ResponseEntity.ok(Map.of("profileImageUrl", fileUrl));
    }
    // 프로필 이미지 삭제
    @DeleteMapping("image/delete")
    public ResponseEntity<?> deleteProfileImage(@RequestParam("memberId") Long memberId) {
        memberService.deleteProfileImage(memberId);
        return ResponseEntity.ok(Map.of("message", "프로필 이미지가 삭제되었습니다."));
    }

    @PutMapping("image/update")
    public ResponseEntity<?> updateProfileImage(
            @RequestParam("memberId") Long memberId,
            @RequestPart("profileImage") MultipartFile profileImage) {

        String newImageKey = memberService.updateProfileImage(memberId, profileImage);
        return ResponseEntity.ok(Map.of("message", "프로필 이미지가 업데이트되었습니다.", "imageKey", newImageKey));
    }
}