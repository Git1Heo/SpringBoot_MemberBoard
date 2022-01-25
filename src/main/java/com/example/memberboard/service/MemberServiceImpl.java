package com.example.memberboard.service;

import com.example.memberboard.dto.MemberDetailDTO;
import com.example.memberboard.dto.MemberLoginDTO;
import com.example.memberboard.dto.MemberSaveDTO;
import com.example.memberboard.entity.MemberEntity;
import com.example.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository mr;

    // 회원가입
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws Exception{

        if (!memberSaveDTO.getMemberFile().isEmpty()) {
            MultipartFile file = memberSaveDTO.getMemberFile();
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            System.out.println("MemberServiceImpl.save fileName : " + fileName );

            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/files";
            System.out.println("MemberServiceImpl.save filePath : " + filePath);

            File saveFile = new File(filePath, fileName);
            file.transferTo(saveFile);

            memberSaveDTO.setMemberFileName(fileName);
        }

        return mr.save(MemberEntity.saveToMemberEntity(memberSaveDTO)).getMemberId();
    }

    // 중복확인
    @Override
    public String idDuplicate(String memberEmail) {
        MemberEntity idDuplicate = mr.findByMemberEmail(memberEmail);
        if(idDuplicate!=null){
            try {
            }
            catch (NullPointerException e){
            }
            finally {
                return "no";
            }
        }
        else
            return "ok";
    }


    // 로그인
    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity=mr.findByMemberEmail(memberLoginDTO.getMemberEmail());

        if(memberEntity != null){
            if(memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    // 관리자용 페이지 전체목록
    @Override
    public List<MemberDetailDTO> adminForm() {
        List<MemberEntity> memberEntityList=mr.findAll();
        List<MemberDetailDTO> memberDetailDTOList=new ArrayList<>();
        for(MemberEntity m: memberEntityList){
            memberDetailDTOList.add(MemberDetailDTO.toDetailDTO(m));
        }
        return memberDetailDTOList;
    }

    // 관리자 멤버 삭제
    @Override
    public void deleteMember(Long memeberId) {
        mr.deleteById(memeberId);
    }


}
