package com.example.memberboard.dto;

import com.example.memberboard.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewDTO {
    private Long boardId;
    private Long boardView;
    private String boardContents;
    private String boardFile;
    private String boardTitle;
}
