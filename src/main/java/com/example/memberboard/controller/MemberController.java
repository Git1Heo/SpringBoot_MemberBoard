package com.example.memberboard.controller;

import com.example.memberboard.dto.MemberDetailDTO;
import com.example.memberboard.dto.MemberLoginDTO;
import com.example.memberboard.dto.MemberSaveDTO;
import com.example.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService ms;
    // 회원가입 폼
    @GetMapping("/save")
    public String saveForm(){
        return "member/save";
    }

    // 회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws Exception{
        Long memberId=ms.save(memberSaveDTO);
        return "index";
    }

    // 아이디 중복확인
    @PostMapping("/idDuplicate")
    public @ResponseBody String idDuplicate(@RequestParam ("id") String memberEmail){
        String result=ms.idDuplicate(memberEmail);
        return result;
    }


    // 로그인 폼
    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session){
        boolean result=ms.login(memberLoginDTO);
        if(result){
            if(memberLoginDTO.getMemberEmail().equals("admin")){
                session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());
                return "redirect:/member/admin";
            }
            else {
                session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
                return "redirect:/board/";
            }
        }
        else
            return "member/login";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    // 관리자용 페이지
    @GetMapping("/admin")
    public String adminForm(Model model){
        List<MemberDetailDTO> memberDetailDTO=ms.adminForm();
        model.addAttribute("member",memberDetailDTO);
        return "member/admin";
    }

    // 관리자 회원 삭제
    @GetMapping("/admin/{memberId}")
    public String deleteMember(@PathVariable Long memberId){
        ms.deleteMember(memberId);
        return "redirect:/member/admin";
    }


}
