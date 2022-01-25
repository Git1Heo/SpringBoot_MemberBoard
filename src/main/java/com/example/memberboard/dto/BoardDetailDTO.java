package com.example.memberboard.dto;

import com.example.memberboard.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardWriter;
    private String boardContents;
    private String boardTitle;
    private Long boardView;
    private String boardFile;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public static BoardDetailDTO toDetailDTO(BoardEntity boardEntity){
        BoardDetailDTO boardDetailDTO=new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getBoardId());
        boardDetailDTO.setBoardWriter(boardEntity.getMemberEntity().getMemberEmail());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardView(boardEntity.getBoardView());
        boardDetailDTO.setBoardFile(boardEntity.getBoardFile());
        boardDetailDTO.setCreateTime(boardEntity.getCreateTime());
        boardDetailDTO.setUpdateTime(boardEntity.getUpdateTime());
        return boardDetailDTO;

    }
}
