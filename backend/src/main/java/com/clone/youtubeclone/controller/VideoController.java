package com.clone.youtubeclone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.clone.youtubeclone.dto.UploadVideoResponse;
import com.clone.youtubeclone.dto.VideoVo;
import com.clone.youtubeclone.service.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UploadVideoResponse> uploadVideo(@RequestParam("file") MultipartFile multipartFile) {
    return ResponseEntity.ok(videoService.uploadVideo(multipartFile));
  }

  @PostMapping("/thumbnail")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UploadVideoResponse> uploadThumbnail(@RequestParam("file") MultipartFile multipartFile,
      @RequestParam("videoId") String videoId) {
    return ResponseEntity.ok(videoService.uploadVideo(multipartFile));
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<VideoVo> editVideoMetaData(@RequestBody VideoVo videoVo) {
    return ResponseEntity.ok(videoService.editVideo(videoVo));
  }

}
