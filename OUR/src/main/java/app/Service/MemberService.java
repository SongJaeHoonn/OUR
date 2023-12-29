package app.Service;

import app.Dto.MemberDto;

import java.util.List;

public interface MemberService {
    void save(MemberDto memberDto);

    MemberDto login(MemberDto memberDto);

    List<MemberDto> findAll();

    MemberDto findById(Long id);

    void update(MemberDto memberDto);

    MemberDto updateForm(String alias);

    void deleteById(Long id);
}
