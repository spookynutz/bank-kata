package com.kata.bank;

import java.time.LocalDateTime;

public class LocalSystemClock implements SystemClock {
    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
