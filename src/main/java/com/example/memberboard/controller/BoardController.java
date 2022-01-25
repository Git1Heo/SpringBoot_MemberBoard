package com.example.memberboard.controller;

import com.example.memberboard.common.PagingConst;
import com.example.memberboard.dto.*;
import com.example.memberboard.service.BoardService;
import com.example.memberboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService bs;
    private final CommentService cs;

    // 페이징
    @GetMapping
    public String paging(@PageableDefault(page=1)Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList=bs.paging(pageable);
        model.addAttribute("boardList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages(); //삼항연산자
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "board/paging";
    }

    // 글 쓰기 폼
    @GetMapping("/save")
    public String saveForm(){
        return "board/save";
    }

    // 글 쓰기 처리
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO, HttpSession session) throws Exception{
        String email=(String) session.getAttribute("loginEmail");
        Long boardId=bs.save(boardSaveDTO,email);
        return "redirect:/board/";
    }

    // 자세히 보기
    @GetMapping("/{boardId}")
    public String findById(@PathVariable Long boardId, Model model){
        // 조휘수
        bs.viewUp(boardId);
        // 상세조회
        BoardDetailDTO boardDetailDTO=bs.findById(boardId);
        model.addAttribute("board", boardDetailDTO);
        // 댓글
        //List<CommentDetailDTO> commentDetailDTOList=bs.commentView(boardId);
        List<CommentDetailDTO> commentDetailDTOList = cs.findAll(boardId);
        model.addAttribute("comment",commentDetailDTOList);

        return "board/findbyid";
    }

    // 글 삭제
    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable Long boardId){
        bs.deleteBoard(boardId);
        return "redirect:/board/";
    }

    // 글 수정 폼
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model){
        BoardDetailDTO boardDetailDTO=bs.updateForm(boardId);
        model.addAttribute("board", boardDetailDTO);
        return "board/update";
    }

    // 글 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO,@RequestParam ("file") String file) throws Exception{
        bs.update(boardUpdateDTO,file);
        return "redirect:/board/"+boardUpdateDTO.getBoardId();
        //return "board/findbyid";
    }

    // 검색
    @GetMapping("/search")
    public String search(@RequestParam ("type") String type,@RequestParam ("keyword") String keyword,Model model,
                         @PageableDefault(size = PagingConst.PAGE_LIMIT, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable){

        List<BoardDetailDTO>  boardDetailDTOList =bs.search(type,keyword,pageable);
        model.addAttribute("boardList",boardDetailDTOList);

        return "board/search";
    }


}
