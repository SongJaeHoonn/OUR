package app.Controller;

import app.Dto.BoardDto;
import app.Service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/our")
public class BoardController {

    BoardService boardService;

    @GetMapping("/create")
    public String createForm(){
        return "create";
    }

    @PostMapping("/main")
    public String create(BoardDto boardDto, HttpSession session){
        boardDto.setMember((String)session.getAttribute("loginMemberId"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        boardDto.setDate(date);
        boardService.createPost(boardDto);
        return "main";
    }

    @GetMapping("/main")
    public String boardList(Model model){
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList",boardDtoList);
        return "main";
    }









}










