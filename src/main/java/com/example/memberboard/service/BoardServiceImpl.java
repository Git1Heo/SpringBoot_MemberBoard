package com.example.memberboard.service;

import com.example.memberboard.common.PagingConst;
import com.example.memberboard.dto.*;
import com.example.memberboard.entity.BoardEntity;
import com.example.memberboard.entity.MemberEntity;
import com.example.memberboard.repository.BoardRepository;
import com.example.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository br;
    private final MemberRepository mr;

    // 페이징
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page=pageable.getPageNumber();
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getBoardId(),
                        board.getMemberEntity().getMemberEmail(),
                        board.getBoardTitle(),
                        board.getBoardView(),
                        board.getCreateTime(),
                        board.getUpdateTime())
        );
        return boardList;
    }

    // 글작성
    @Override
    public Long save(BoardSaveDTO boardSaveDTO,String email) throws Exception{
        if (!boardSaveDTO.getBoardFile().isEmpty()) {
            MultipartFile file = boardSaveDTO.getBoardFile();
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/files";

            File saveFile = new File(filePath, fileName);
            file.transferTo(saveFile);

            boardSaveDTO.setBoardFileName(fileName);
        }
        MemberEntity memberEntity=mr.findByMemberEmail(email);
        return br.save(BoardEntity.saveToEntity(boardSaveDTO,memberEntity)).getBoardId();
    }

    // 글 상세조회
    @Override
    public BoardDetailDTO findById(Long boardId) {
//        BoardEntity boardEntity=br.findById(boardId).get();
//        MemberEntity memberEntity=mr.findById(boardEntity.getMemberEntity().getMemberId()).get();
//        BoardViewDTO boardViewDTO=new BoardViewDTO(boardId,boardEntity.getBoardView()+1,boardEntity.getBoardContents(),boardEntity.getBoardFile(), boardEntity.getBoardTitle());
//        br.save(BoardEntity.viewToEntity(boardViewDTO,memberEntity));
        return BoardDetailDTO.toDetailDTO(br.findById(boardId).get());
    }

    // 조회수 증가
    @Override
    @Transactional
    public void viewUp(Long boardId) {
        br.viewUp(boardId);
    }

    // 글 삭제
    @Override
    public void deleteBoard(Long boardId) {
        br.deleteById(boardId);
    }

    // 글 수정 폼
    @Override
    public BoardDetailDTO updateForm(Long boardId) {
        return BoardDetailDTO.toDetailDTO(br.findById(boardId).get());
    }

    // 글 수정
    @Override
    public void update(BoardUpdateDTO boardUpdateDTO,String filex) throws Exception {
        if (!boardUpdateDTO.getBoardFile().isEmpty()) {
            MultipartFile file = boardUpdateDTO.getBoardFile();
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();

            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/files";

            File saveFile = new File(filePath, fileName);
            file.transferTo(saveFile);

            boardUpdateDTO.setBoardFileName(fileName);
        }
        else{
            boardUpdateDTO.setBoardFileName(filex);
        }

        MemberEntity memberEntity=mr.findByMemberEmail(boardUpdateDTO.getMemberEmail());

         br.save(BoardEntity.updateToEntity(boardUpdateDTO,memberEntity));
    }

    // 글 검색
    @Override
    @Transactional
    public List<BoardDetailDTO> search(String type, String keyword,Pageable pageable) {

        List<BoardEntity> boardEntityList=new ArrayList<>();
        if(type.equals("boardTitle")) {
            boardEntityList = br.findByBoardTitleContaining(keyword, pageable);
        }
        else if(type.equals("boardWriter")) {
            boardEntityList = br.searchWriter(keyword,pageable);
        }

        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();

        if(boardEntityList.isEmpty()) {
            System.out.println("BoardServiceImpl.search 검색결과 없음");
            return boardDetailDTOList;
        }

        for(BoardEntity b: boardEntityList){
            boardDetailDTOList.add(BoardDetailDTO.toDetailDTO(b));
        }

        return boardDetailDTOList;
    }




}
