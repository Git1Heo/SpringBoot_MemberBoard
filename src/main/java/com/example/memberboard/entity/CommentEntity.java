package com.example.memberboard.entity;

import com.example.memberboard.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long commentId;

    @Column
    private String commentContents;

    // 보드아이디 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardEntity boardEntity;

    // 댓글 작성자 멤버이메일 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;

    public static CommentEntity toEntity(CommentSaveDTO commentSaveDTO,BoardEntity boardEntity, MemberEntity memberEntity ){
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        return commentEntity;
    }

}
