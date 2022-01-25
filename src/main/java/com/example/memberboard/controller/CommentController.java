package com.example.memberboard.controller;

import com.example.memberboard.dto.CommentDetailDTO;
import com.example.memberboard.dto.CommentSaveDTO;
import com.example.memberboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService cs;

    @PostMapping("/save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO){
        System.out.println("commentSaveDTO = " + commentSaveDTO);
        Long commentId=cs.save(commentSaveDTO);
        List <CommentDetailDTO> commentDetailDTOList=cs.findAll(commentSaveDTO.getBoardId());
        return commentDetailDTOList;
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam ("commentId") Long commentId){
        cs.delete(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
