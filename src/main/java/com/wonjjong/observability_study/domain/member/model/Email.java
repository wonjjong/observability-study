package com.wonjjong.observability_study.domain.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.regex.Pattern;

@Embeddable
public record Email(@Column(name="email_address", length = 150, nullable = false) String address) implements Serializable {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+-]+(?:\\.[a-zA-Z0-9.-]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$");
    public Email {
        if(!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("이메일 형식이 바르지 않습니다: " + address);
        }
    }
}

