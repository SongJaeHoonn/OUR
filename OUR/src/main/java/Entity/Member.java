package Entity;

import Dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_table")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String alias;

    public static Member toMember(MemberDto memberDto){
        Member member = new Member();
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setAlias(memberDto.getAlias());
        return member;
    }

}
