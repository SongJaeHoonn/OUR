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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute MemberDto memberDto, Errors errors, Model model){
        if (errors.hasErrors()) {
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("memberDto", memberDto);

            // 유효성 검사 에러 처리
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 회원가입 페이지로 다시 리턴
            return "save";
        }
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
