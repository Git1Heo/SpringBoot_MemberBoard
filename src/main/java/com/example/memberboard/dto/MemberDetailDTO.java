package com.example.memberboard.dto;

import com.example.memberboard.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberFile;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static MemberDetailDTO toDetailDTO(MemberEntity memberEntity){
        MemberDetailDTO memberDetailDTO=new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getMemberId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        memberDetailDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDetailDTO.setMemberFile(memberEntity.getMemberFile());
        memberDetailDTO.setCreateTime(memberEntity.getCreateTime());
        memberDetailDTO.setUpdateTime(memberEntity.getUpdateTime());
        return memberDetailDTO;
    }
}
