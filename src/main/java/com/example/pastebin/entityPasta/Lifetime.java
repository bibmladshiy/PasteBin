package com.example.pastebin.entityPasta;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Arrays;

@Getter
@Slf4j
public enum Lifetime {
    MIN_10(Duration.ofMinutes(10)),
    HOUR_1(Duration.ofHours(1)),
    HOUR_3(Duration.ofHours(3)),
    DAY_1(Duration.ofDays(1)),
    WEEK_1(Duration.ofDays(7)),
    MONTH_1(Duration.ofDays(30)),
    INFINITE(Duration.ofDays(1000000000));
    private final Duration duration;
    Lifetime(Duration duration) {
        this.duration = duration;
    }

    public static Lifetime fromValue(String value) {
        for (Lifetime lifetime : values()) {
            if (lifetime.toString().equalsIgnoreCase(value)) {
                return lifetime;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
