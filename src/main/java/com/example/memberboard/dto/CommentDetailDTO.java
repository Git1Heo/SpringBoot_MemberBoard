package com.example.memberboard.dto;

import com.example.memberboard.entity.CommentEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class CommentDetailDTO {
    private Long commentId;
    private String commentContents;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long boardId;
    private String memberEmail;

    public static CommentDetailDTO toCommentDetailDTO(CommentEntity commentEntity){
        CommentDetailDTO commentDetailDTO=new CommentDetailDTO();
        commentDetailDTO.setCommentId(commentEntity.getCommentId());
        commentDetailDTO.setCommentContents(commentEntity.getCommentContents());
        commentDetailDTO.setCreateTime(commentEntity.getCreateTime());
        commentDetailDTO.setUpdateTime(commentEntity.getUpdateTime());
        commentDetailDTO.setBoardId(commentEntity.getBoardEntity().getBoardId());
        commentDetailDTO.setMemberEmail(commentEntity.getMemberEntity().getMemberEmail());
        return commentDetailDTO;
    }
}
