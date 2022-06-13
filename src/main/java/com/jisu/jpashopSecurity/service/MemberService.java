package com.jisu.jpashopSecurity.service;

import com.jisu.jpashopSecurity.domain.Member;
import com.jisu.jpashopSecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {

        String password = bCryptPasswordEncoder.encode(member.getPassword());
        member.hasRole("ROLE_USER");
        member.encodePassword(password);
        memberRepository.save(member);
        return member.getId();
    }

    public void memberCheck(String email) {
        validateDuplicateMember(email);
    }
    private void validateDuplicateMember(String email){
        List<Member> findMembers = memberRepository.findByEmail(email);
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
