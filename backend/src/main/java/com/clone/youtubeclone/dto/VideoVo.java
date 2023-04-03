package com.clone.youtubeclone.dto;

import com.clone.youtubeclone.model.Comment;
import com.clone.youtubeclone.model.VideoStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoVo {

    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String url;
    private VideoStatus videoStatus;
    private String thumbnailUrl;


}
