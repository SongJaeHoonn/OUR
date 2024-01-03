package app.Controller;

import app.Dto.MemberDto;
import app.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            httpSession.setAttribute("loginAlias", loginresult.getAlias());
            return "main";
        }else{
            return "login";
        }
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model){
        String alias = (String)session.getAttribute("loginAlias");
        MemberDto memberDto = memberService.updateForm(alias);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping ("/update")
    public String update(@ModelAttribute MemberDto memberDto){
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }



}
