package com.fjern.common.util;

import java.util.Random;

public class IdUtil {

    public final static Long randomPositiveLong() {
        Long randomLong = new Random().nextLong() * 10000;
        randomLong = (randomLong > 0) ? randomLong : randomLong * (-1L);
        return randomLong;
    }
}
