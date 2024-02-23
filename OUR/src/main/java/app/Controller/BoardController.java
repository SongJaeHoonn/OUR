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
        return "view";
    }

    @GetMapping("/search")
    public String searchList(@RequestParam("query") String query, Model model){
        List<BoardDto> list = boardService.findByHead(query);
        model.addAttribute("boardList", list);
        return "searchList";
    }

    @GetMapping("/updatePost/{id}")
    public String updatePostForm(@PathVariable("id") Long boardId, Model model){
        model.addAttribute("board", boardService.updatePostForm(boardId));
        return "updatePost";
    }

    @PostMapping("/update")
    public String updatePost(MultipartFile file, @ModelAttribute BoardDto boardDto) throws IOException {
        Date date = boardService.updatePostForm(boardDto.getBoardId()).getDate();
        boardDto.setDate(date);
        boardService.updatePost(boardDto, file);
        return "redirect:/our/main";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable("id") Long boardId){
        boardService.deletePostByBoardId(boardId);
        return "redirect:/our/main";
    }
}









