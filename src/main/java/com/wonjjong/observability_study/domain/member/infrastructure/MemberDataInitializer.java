package com.wonjjong.observability_study.domain.member.infrastructure;

import com.wonjjong.observability_study.domain.member.model.Member;
import com.wonjjong.observability_study.domain.member.model.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberDataInitializer implements ApplicationRunner {

    private static final int BATCH_SIZE = 100;
    private static final int TOTAL_MEMBERS = 100;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.info("Member 테스트 데이터 {}건 생성 시작...", TOTAL_MEMBERS);

        List<Member> members = new ArrayList<>(BATCH_SIZE);

        for (int i = 1; i <= TOTAL_MEMBERS; i++) {
            MemberRegisterRequest request = new MemberRegisterRequest(
                    "user%04d@test.com".formatted(i),
                    "사용자%04d".formatted(i),
                    "password123!"
            );

            Member member = Member.register(request, passwordEncoder);
            members.add(member);

            if (i % BATCH_SIZE == 0) {
                memberRepository.saveAll(members);
                members.clear();
                log.info("{}건 저장 완료", i);
            }
        }
        log.info("Member 테스트 데이터 {}건 생성 완료!", TOTAL_MEMBERS);
    }
}

