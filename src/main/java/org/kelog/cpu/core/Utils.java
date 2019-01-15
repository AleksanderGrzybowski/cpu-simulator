package org.kelog.cpu.core;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

class Utils {
    static <T, U> Map<T, U> makeMap(T[] values, U defaultValue) {
        return Arrays.stream(values).collect(toMap(k -> k, v -> defaultValue));
    }
    
    static String formatNumber(int number) {
        return String.format("%4d", number);
    }
}
