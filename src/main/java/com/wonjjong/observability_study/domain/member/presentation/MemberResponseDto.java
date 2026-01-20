package com.wonjjong.observability_study.domain.member.presentation;

import com.wonjjong.observability_study.domain.member.model.Member;

public record MemberResponseDto(Long memberId, String email) {
    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getId(), member.getEmail().address());
    }
}
