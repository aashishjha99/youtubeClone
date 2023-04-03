package com.clone.youtubeclone.service;

import com.clone.youtubeclone.dto.UploadVideoResponse;
import com.clone.youtubeclone.dto.VideoVo;
import org.springframework.web.multipart.MultipartFile;

/*

 */

public interface VideoService {

  UploadVideoResponse uploadVideo(MultipartFile multipartFile);

  VideoVo editVideo(VideoVo videoVo);
}
