package com.example.memberboard.service;

import com.example.memberboard.dto.CommentDetailDTO;
import com.example.memberboard.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);

    void delete(Long commentId);
}
