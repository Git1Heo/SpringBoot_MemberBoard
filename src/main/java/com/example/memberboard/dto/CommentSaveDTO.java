package com.example.memberboard.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentSaveDTO {
    private String commentContents;
    private String  memberEmail;
    private Long boardId;
}
