package com.clone.youtubeclone.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String fullName;
    private String emailAddress;

    private Set<String> subsribedToUsers;

    private Set<String> subsribed;

    private List<String> videoHistory;

    private Set<String> likedVideos;

    private Set<String> dislikedVideos;


}
