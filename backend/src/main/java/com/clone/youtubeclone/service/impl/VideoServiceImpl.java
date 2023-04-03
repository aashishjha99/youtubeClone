package com.clone.youtubeclone.service.impl;

import com.clone.youtubeclone.dto.UploadVideoResponse;
import com.clone.youtubeclone.dto.VideoVo;
import com.clone.youtubeclone.model.Video;
import com.clone.youtubeclone.repository.VideoRepository;
import com.clone.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final S3ServiceImpl s3Service;

  private final VideoRepository videoRepository;

  @Override
  public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {

    // upload to s3
    String s3VideoUrl = s3Service.uploadFile(multipartFile);

    // save in db
    var videoUrl = Video.builder().url(s3VideoUrl).build();
    var videoOut = videoRepository.save(videoUrl);
    return new UploadVideoResponse(videoOut.getId(), videoOut.getUrl());
  }

  @Override
  public VideoVo editVideo(VideoVo videoVo) {

    Video videoSaved = videoRepository.findById(videoVo.getId())
        .orElseThrow(() -> new IllegalArgumentException("cannot find by id -" + videoVo.getId()));
    videoSaved.setTags(videoVo.getTags());
    videoSaved.setThumbnailUrl(videoVo.getThumbnailUrl());
    videoSaved.setVideoStatus(videoVo.getVideoStatus());
    videoSaved.setDescription(videoVo.getDescription());
    videoSaved.setTitle(videoVo.getTitle());
    videoRepository.save(videoSaved);
    return videoVo;

  }

}
