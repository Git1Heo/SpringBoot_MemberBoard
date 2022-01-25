package com.example.memberboard.service;

import com.example.memberboard.dto.MemberDetailDTO;
import com.example.memberboard.dto.MemberLoginDTO;
import com.example.memberboard.dto.MemberSaveDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws Exception;

    boolean login(MemberLoginDTO memberLoginDTO);

    String idDuplicate(String memberEmail);

    List<MemberDetailDTO> adminForm();

    void deleteMember(Long memeberId);
}
