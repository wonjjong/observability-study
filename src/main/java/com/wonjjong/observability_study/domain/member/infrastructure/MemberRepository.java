package com.wonjjong.observability_study.domain.member.infrastructure;

import com.wonjjong.observability_study.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

