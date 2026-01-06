package com.wonjjong.observability_study;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Timed("member.service")
public class MemberService {

    @Counted("member.service")
    public void postMember(){
        log.info("memberService >> postMember");
    }

    @Counted("member.service")
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
}
