package app.Controller;

import app.Dto.MemberDto;
import app.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping ("/our")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession httpSession){
        MemberDto loginresult = memberService.login(memberDto);
        if(loginresult != null){
            httpSession.setAttribute("loginAlias", loginresult.getAlias());
            httpSession.setAttribute("loginMemberId", loginresult.getMemberId());
            httpSession.setMaxInactiveInterval(600);
            return "main";
        }else{
            return "redirect:/member/login";
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

    @PostMapping ("/login")
    public String update(@ModelAttribute MemberDto memberDto){
        memberService.update(memberDto);
        return "login";
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
