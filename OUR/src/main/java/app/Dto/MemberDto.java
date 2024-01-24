package app.Dto;

import app.Entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private Long id;
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String memberId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{5,10}$",
    message = "문자, 숫자, 특수문자를 포함한 최소 5자리, 최대 10자리의 비밀번호입니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String alias;

    public static MemberDto toMemberDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setMemberId(member.getMemberId());
        memberDto.setName(member.getName());
        memberDto.setPassword(member.getPassword());
        memberDto.setAlias(member.getAlias());
        return memberDto;
    }
}
