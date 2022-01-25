package com.example.memberboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPagingDTO {
    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private Long boardView;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}