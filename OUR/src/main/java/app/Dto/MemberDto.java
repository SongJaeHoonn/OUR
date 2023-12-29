package app.Dto;

import app.Entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private Long id;
    private String password;
    private String name;
    private String alias;

    public static MemberDto toMemberDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setPassword(member.getPassword());
        memberDto.setAlias(member.getAlias());
        return memberDto;
    }
}
