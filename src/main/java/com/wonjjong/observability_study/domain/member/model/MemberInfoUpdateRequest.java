package com.wonjjong.observability_study.domain.member.model;

public record MemberInfoUpdateRequest(
        String profileAddress,
        String introduction,
        String nickname
) {
}
