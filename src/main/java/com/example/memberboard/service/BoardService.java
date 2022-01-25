package com.example.memberboard.service;

import com.example.memberboard.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Page<BoardPagingDTO> paging(Pageable pageable);

    Long save(BoardSaveDTO boardSaveDTO,String email) throws Exception;

    BoardDetailDTO findById(Long boardId);

    void viewUp(Long boardId);

    void deleteBoard(Long boardId);

    BoardDetailDTO updateForm(Long boardId);

    void update(BoardUpdateDTO boardUpdateDTO,String file) throws Exception;

    List<BoardDetailDTO> search(String type, String keyword,Pageable pageable);

}
