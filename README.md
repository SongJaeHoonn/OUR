## 아워프로젝트 :

### 우리만 아는 네트워크를 만들기 위함

1. 회원가입/로그인 기능
   * : 관리자 로그인 (관리자 페이지 들어감)
    1. 아이디, 비밀번호, 이름, 별칭 설정
    2. 로그인 시에는 아이디와 비밀번호만 입력
    3. 중복 검사(아이디 8자 이상 입력/ 비밀번호 8자 이상 입력(영문, 숫자, 특수문자 포함)
3. 사람 검색 가능, 초대 가능
4. 글 작성
    1. 타이틀, 태그, 본문(사진 or 영상 가능)
    2. 글 생성, 삭제, 수정 기능
5. 댓글 기능
    1. 글에 댓글 작성 가능
    2. 댓글 삭제 기능
6. 대댓글 기능
    1. 댓글에 대댓글 작성 가능
7. 좋아요 기능
8. 코인 기능(개인마다 코인 부여, 글 작성, 댓글 작성시 포인트 추가)
9. 게임장
    1. 코인으로 비용 제출하고 게임 가능
    2. 90 → 70 → 50 → 30 → 10퍼센트 확률로 낸 비용의 배로 돌려받음
    3. 90:1.1배 70: 1.3배 50: 2배 30: 4배 10:10배
10. 채팅 기능
    1. 초대된 사람들끼리 채팅 가능
(기간:2학년 겨울방학)


-----------------------------------------------------------------------------------------------

### 피드백 사항

1. 스프링 시큐리티 이용한 비밀번호 복호화 O
1) : 비밀번호 변경 탭을 따로 둬서 비밀번호만 변경 가능하게 해야할 듯? - 이거 상당히 어려움 변경할때 암호화 로직을 다시 짜야할듯
2) 일단 회원부 종결하고, 비번 변경 나중에 가자
2. 회원가입시 비밀번호 확인 추가 O
3. 정규식을 이용한 비밀번호 특정 문자 포함 여부 결정 O
4. 관리자와 일반 사용자 구분
   1) 이거 관리자 페이지를 완전 새로 만들어야되니까(아직 구상도 못함) 일단 만들던거 다 만들고 시작
6. 리다이렉트를 인터셉트 핸들러로 바꿔보기(이거는 시간 되면 해보자)


-----------------------------------------------------------------------
### 앞으로 해야할 것

1. 비밀번호 변경
2. 댓글 기능(대댓글, 좋아요)
3. 코인, 게임장, 채팅...(이거 할 수 있을까..? 시간 안에 못할듯..?)
4. 관리자 페이지 / 개인 페이지(이거 할려면 엔티티간의 관계를 잘 만들어놨어야 했는데 너무 중구난방이라 하기 힘들듯 ㅠㅠ)

