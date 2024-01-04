package app.Controller;

import app.Dto.MemberDto;
import app.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDto memberDto){
        System.out.println(memberDto);
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping ("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession httpSession){
        MemberDto loginresult = memberService.login(memberDto);
        if(loginresult != null){
            httpSession.setAttribute("loginMemberId", loginresult.getMemberId());
            return "main";
        }else{
            return "login";
        }
    }
    @GetMapping("/")
    public String findAll(Model model){
        List<MemberDto> memberDTOList = memberService.findAll();
        //어떠한 html로 가져갈 데이터 있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model){
        String memberId = (String)session.getAttribute("loginMemberId");
        MemberDto memberDto = memberService.updateForm(memberId);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping ("/update")
    public String update(@ModelAttribute MemberDto memberDto){
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }



}
