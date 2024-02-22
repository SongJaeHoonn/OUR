package app.Controller;

import app.Dto.MemberDto;
import app.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"loginAlias", "loginMemberId"})
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "save";
    }

    @PostMapping("/save")
    public String save(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "save";
        }
        try{
            memberService.save(memberDto);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "save";
        }
        return "login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping ("/our")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession httpSession){
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            httpSession.setAttribute("loginAlias", loginResult.getAlias());
            httpSession.setAttribute("loginMemberId", loginResult.getMemberId());
            httpSession.setMaxInactiveInterval(1800);
            return "redirect:/our/main";
        }else{

            return "redirect:/member/login";
        }
    }
    @GetMapping("/")
    public String findAll(Model model){
        List<MemberDto> memberDTOList = memberService.findAll();
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

