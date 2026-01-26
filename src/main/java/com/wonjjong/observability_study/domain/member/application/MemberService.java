package com.wonjjong.observability_study.domain.member.application;

import com.wonjjong.observability_study.domain.member.infrastructure.MemberRepository;
import com.wonjjong.observability_study.domain.member.model.Member;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Timed("member.service")
@Counted("member.service")
public class MemberService {
    private final MemberRepository memberRepository;

    public void postMember(){
        log.info("memberService >> postMember");
    }

    public void deleteMember(){
        log.info("memberService >> deleteMember");
    }

    public void getMember() {
        log.info("memberService >> getMember");
        sleep(1000);
    }

    private static void sleep(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Cacheable("member")
    public List<Member> getAllMemberUsingCache() {
        return memberRepository.findAllWithDetail();
    }
}
