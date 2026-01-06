package com.wonjjong.observability_study;

import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
@Counted("member.controller")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void postMember(){
        log.info("memberController >> postMember");
        memberService.postMember();
    }

    @DeleteMapping
    public void deleteMember() {
        log.info("memberController >> deleteMember");
        memberService.deleteMember();
    }
}
