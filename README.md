# MemeberBoard 

###댓글 삭제
~~~
            <div th:if="${(session['loginEmail'])=='admin'} or ${comment.memberEmail.equals(session['loginEmail'])}">
                <td><button th:id="${comment.commentId}" th:value="${comment.commentId}" th:class="${comment.commentId}" onclick="deleteComment(this.id)">삭제</button></td>
            </div>
~~~
- 댓글 작성자나 관리자가 댓글 삭제 가능
- 버튼 클릭시 해당 댓글의 댓글 번호를 자바스크립트로 컨트롤러부분으로 보내 댓글 삭제하는데 사용
- 댓글 삭제후 페이지를 재 출력

### 검색
~~~
    List <BoardEntity> findByBoardTitleContaining(String keyword, Pageable pageable);
    
    @Query(value = "SELECT a FROM BoardEntity a WHERE a.memberEntity.memberEmail like concat('%',:writer,'%')")
    public List<BoardEntity> searchWriter(String writer,Pageable pageable);
~~~
- 제목 검색시에는 Containing을 사용하여 검색
- 작성자 검색시에는 jpql 사용하여 검색
- 검색결과 페이징 하기위해 Pageable도 같이 매개변수로 넣어줌
