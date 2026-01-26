package com.wonjjong.observability_study.domain.member.infrastructure;

import com.wonjjong.observability_study.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m JOIN FETCH m.detail")
    List<Member> findAllWithDetail();
}

