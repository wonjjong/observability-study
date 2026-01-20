package com.wonjjong.observability_study.domain.member.model;


import com.wonjjong.observability_study.global.model.AbstractEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded // Profile 클래스에 정의된 필드들이 MEMBER 테이블의 컬럼으로 생성됩니다.
    private Profile profile;

    private String introduction;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false) // DB 외래키 컬럼명
    private Member member;

    protected static MemberDetail create(Member member) {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.member = member;
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    protected void activate() {
        Assert.isTrue(activatedAt == null, "이미 activatedAt은 설정되었습니다.");

        this.activatedAt = LocalDateTime.now();
    }

    protected void deactivate() {
        Assert.isTrue(deactivatedAt == null, "이미 deactivatedAt은 설정되었습니다.");

        this.deactivatedAt = LocalDateTime.now();

    }

    void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.profile = new Profile(updateRequest.profileAddress());
        this.introduction = Objects.requireNonNull(updateRequest.introduction());
    }
}
