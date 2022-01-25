package com.example.memberboard.service;

import com.example.memberboard.dto.CommentDetailDTO;
import com.example.memberboard.dto.CommentSaveDTO;
import com.example.memberboard.entity.BoardEntity;
import com.example.memberboard.entity.CommentEntity;
import com.example.memberboard.entity.MemberEntity;
import com.example.memberboard.repository.BoardRepository;
import com.example.memberboard.repository.CommentRepository;
import com.example.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final BoardRepository br;
    private final MemberRepository mr;
    private final CommentRepository cr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        MemberEntity memberEntity = mr.findByMemberEmail(commentSaveDTO.getMemberEmail());

        CommentEntity commentEntity=CommentEntity.toEntity(commentSaveDTO,boardEntity,memberEntity);

        return cr.save(commentEntity).getCommentId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity=br.findById(boardId).get();
        List <CommentEntity> commentEntityList=boardEntity.getCommentEntityList();
        List <CommentDetailDTO> commentDetailDTOList=new ArrayList<>();

        for(CommentEntity c: commentEntityList){
            CommentDetailDTO commentDetailDTO=CommentDetailDTO.toCommentDetailDTO(c);
            commentDetailDTOList.add(commentDetailDTO);
        }

        return commentDetailDTOList;
    }

    @Override
    public void delete(Long commentId) {
        cr.deleteById(commentId);
    }
}
