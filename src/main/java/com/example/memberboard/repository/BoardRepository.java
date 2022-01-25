package com.example.memberboard.repository;

import com.example.memberboard.entity.BoardEntity;
import com.example.memberboard.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository <BoardEntity,Long> {

    @Modifying
    @Query("update BoardEntity p set p.boardView = p.boardView + 1 where p.boardId = :boardId")
    int viewUp(Long boardId);

    List <BoardEntity> findByBoardTitleContaining(String keyword, Pageable pageable);

    @Query(value = "SELECT a FROM BoardEntity a WHERE a.memberEntity.memberEmail like concat('%',:writer,'%')")
    public List<BoardEntity> searchWriter(String writer,Pageable pageable);

}
