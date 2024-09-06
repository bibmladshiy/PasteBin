package com.example.kal_l.entityPasta;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
@RequiredArgsConstructor
@Slf4j
public enum PastaStatus{

    PRIVATE, UNLISTED;
    public static PastaStatus fromValue(String value) {
        for (PastaStatus status : values()) {
            log.info(String.valueOf(status));
            if (status.toString().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
