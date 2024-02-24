package app.Service;

import app.Dto.MemberDto;
import app.Entity.Member;
import app.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(MemberDto memberDto) {
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = Member.toMember(memberDto);
        memberRepository.save(member);
    }


    public MemberDto login(MemberDto memberDto) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(memberDto.getMemberId());
        if(byMemberId.isPresent()){
            Member member = byMemberId.get();
            String lawPassword = memberDto.getPassword();
            String encodedPassword = member.getPassword();
            if(validationPW(lawPassword, encodedPassword)){
                return MemberDto.toMemberDto(member);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Boolean validationPW(String lawPassword, String encodedPassword){
        if(passwordEncoder.matches(lawPassword, encodedPassword)){
            return true;
        }else{
            return false;
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

    public Member findByMemberId(String memberId){
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if(member.isPresent()){
            return member.get();
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

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
