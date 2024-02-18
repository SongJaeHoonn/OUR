package app.Controller;
import app.Dto.BoardDto;
import app.Service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/our")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/create")
    public String createForm() {
        return "create";
    }

    @PostMapping("/main")
    public String create(BoardDto boardDto, HttpSession session, MultipartFile file) throws IOException {

        String member = (String) session.getAttribute("loginMemberId");
        boardDto.setMember(member);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Timestamp를 java.sql.Date로 변환합니다.
        Date date = new Date(timestamp.getTime());
        // BoardDto에 날짜 정보를 설정합니다.
        boardDto.setDate(date);
        boardService.createPost(boardDto, file);

        // 생성된 게시물의 상세 정보 페이지로 리디렉션합니다.
        return "redirect:/our/main";
    }

    @GetMapping("/main")
    public String boardList(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        return "main";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Long boardId, Model model){
        BoardDto boardDto = boardService.findById(boardId);

        model.addAttribute("board", boardDto);
        System.out.println(boardDto.getMember());
        System.out.println(boardDto.getFileName());
        System.out.println(boardDto.getFilePath());
        return "view";
    }



}









