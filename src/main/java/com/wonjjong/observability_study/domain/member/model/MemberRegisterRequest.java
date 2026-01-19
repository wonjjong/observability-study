package com.wonjjong.observability_study.domain.member.model;

public record MemberRegisterRequest(
        String email,
        String nickname,
        String password
) {
}
