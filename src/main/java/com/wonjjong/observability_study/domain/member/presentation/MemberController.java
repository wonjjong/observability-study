package com.wonjjong.observability_study.domain.member.presentation;

import com.wonjjong.observability_study.domain.member.application.MemberService;
import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<MemberResponseDto> getAllMember () {
        return memberService.getAllMember().stream().map(MemberResponseDto::of).toList();
    }

    @GetMapping("/cache")
    public List<MemberResponseDto> getAllMemberUsingCache () {
        return memberService.getAllMemberUsingCache().stream().map(MemberResponseDto::of).toList();
    }
}
