package app.Controller;

import app.Dto.MemberDto;
import app.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDto memberDto){
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberDto memberDto, HttpSession httpSession){
        MemberDto loginresult = memberService.login(memberDto);
        if(loginresult != null){
            httpSession.setAttribute("loginAlias", loginresult.getAlias());
            return "main";
        }else{
            return "login";
        }
    }


}
