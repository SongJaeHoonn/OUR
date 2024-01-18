package app.Dto;

import app.Entity.Member;
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
    private String memberId;
    @Size(min=5, max=10)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{5,10}$",
    message = "문자, 숫자, 특수문자를 포함한 최소 5자리, 최대 10자리의 비밀번호")
    private String password;
    private String name;
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
