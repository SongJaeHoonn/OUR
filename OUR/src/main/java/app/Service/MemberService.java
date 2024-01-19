package app.Service;

import app.Dto.MemberDto;
import app.Entity.Member;
import app.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {
        Member member = Member.toMember(memberDto);
        memberRepository.save(member);
    }


    public MemberDto login(MemberDto memberDto) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(memberDto.getMemberId());
        if(byMemberId.isPresent()){
            Member member = byMemberId.get();
            if(member.getPassword().equals(memberDto.getPassword())){
                MemberDto dto = MemberDto.toMemberDto(member);
                return dto;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public List<MemberDto> findAll() {
        List<Member> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for(Member member : memberEntityList){
            memberDtoList.add(MemberDto.toMemberDto(member));
        }
        return memberDtoList;
    }


    public MemberDto findById(Long id) {
        Optional<Member> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(Member.toUpdateMember(memberDto));
    }

    public MemberDto updateForm(String memberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if(optionalMember.isPresent()){
            return MemberDto.toMemberDto(optionalMember.get());
        }else{
            return null;
        }
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
