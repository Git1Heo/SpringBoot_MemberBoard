package com.example.memberboard.entity;

import com.example.memberboard.dto.MemberLoginDTO;
import com.example.memberboard.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberFile;

    @Column
    private String memberPhone;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity saveToMemberEntity(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPhone(memberSaveDTO.getMemberPhone());
        memberEntity.setMemberFile(memberSaveDTO.getMemberFileName());
        return memberEntity;
    }

    public static MemberEntity loginToMemberEntity(MemberLoginDTO memberLoginDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setMemberEmail(memberLoginDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberLoginDTO.getMemberPassword());
        return memberEntity;
    }



}
