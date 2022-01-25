package com.example.memberboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDTO {
    private Long boardId;
    private String memberEmail;
    private String boardTitle;
    private String boardContents;
    private MultipartFile boardFile;
    private String boardFileName;
    private Long boardView;
}
