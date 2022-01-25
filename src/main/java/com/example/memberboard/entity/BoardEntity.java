package com.example.memberboard.entity;

import com.example.memberboard.dto.BoardDetailDTO;
import com.example.memberboard.dto.BoardSaveDTO;
import com.example.memberboard.dto.BoardUpdateDTO;
import com.example.memberboard.dto.BoardViewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private Long boardView;

    @Column
    private String boardFile;

    // 게시글 작성자 멤버이메일 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;

    // 댓글의 보드아이디 참조
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList=new ArrayList<>();

    public static BoardEntity saveToEntity(BoardSaveDTO boardSaveDTO,MemberEntity memberEntity){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardView(0L);
        boardEntity.setBoardFile(boardSaveDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    public static BoardEntity viewToEntity(BoardViewDTO boardViewDTO,MemberEntity memberEntity){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardId(boardViewDTO.getBoardId());
        boardEntity.setBoardView(boardViewDTO.getBoardView());
        boardEntity.setBoardContents(boardViewDTO.getBoardContents());
        boardEntity.setBoardFile(boardViewDTO.getBoardFile());
        boardEntity.setBoardTitle(boardViewDTO.getBoardTitle());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    public static BoardEntity updateToEntity(BoardUpdateDTO boardUpdateDTO,MemberEntity memberEntity){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardView(boardUpdateDTO.getBoardView());
        boardEntity.setBoardFile(boardUpdateDTO.getBoardFileName());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        return boardEntity;
    }

}
