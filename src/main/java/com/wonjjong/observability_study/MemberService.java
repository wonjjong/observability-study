package com.wonjjong.observability_study;

import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {

    @Counted("member.service")
    public void postMember(){
        log.info("memberService >> postMember");
    }

    @Counted("member.service")
    public void deleteMember(){
        log.info("memberService >> deleteMember");
    }
}
