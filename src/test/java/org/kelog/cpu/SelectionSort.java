package org.kelog.cpu;

import org.junit.Test;
import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Environment;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.kelog.cpu.TestUtils.loadProgram;

public class SelectionSort {
    
    private Environment.Builder environmentBuilder = new Environment.Builder().withLogging(false);
    
    private List<List<Integer>> TEST_DATA = Arrays.asList(
            asList(9, 32, 59, 64, 24, 84, 38, 33, 32, 68, 33, 97, 61, 23, 75, 91),
            asList(53, 93, 49, 71, 29, 88, 65, 58, 88, 1, 59, 6, 44, 40, 92, 79),
            asList(35, 41, 61, 70, 44, 45, 98, 72, 8, 63, 54, 59, 78, 89, 87, 3),
            asList(74, 44, 98, 23, 91, 35, 3, 48, 13, 28, 18, 63, 17, 44, 68, 12),
            asList(0, 18, 98, 17, 80, 4, 74, 21, 7, 86, 74, 90, 5, 12, 72, 35),
            asList(93, 36, 46, 75, 87, 43, 70, 3, 56, 34, 9, 83, 33, 49, 4, 98),
            asList(58, 49, 28, 7, 98, 46, 97, 56, 80, 60, 76, 83, 58, 23, 67, 46),
            asList(25, 60, 67, 23, 80, 72, 53, 77, 24, 51, 4, 76, 86, 58, 23, 58),
            asList(4, 89, 89, 93, 39, 85, 39, 14, 47, 34, 13, 57, 27, 48, 63, 28),
            asList(44, 31, 94, 10, 77, 71, 6, 69, 43, 0, 69, 52, 73, 34, 45, 50),
            asList(95, 10, 92, 99, 25, 91, 7, 95, 63, 26, 85, 27, 46, 72, 17, 68),
            asList(29, 73, 75, 46, 14, 12, 12, 36, 20, 20, 39, 98, 28, 92, 97, 60)
    );
    
    @Test
    public void selectionsort() {
        TEST_DATA.forEach(this::runTest);
    }
    
    private void runTest(List<Integer> data) {
        CpuState finalState = environmentBuilder.withProgram(loadProgram("selectionsort.txt"))
                .withInitialMemory(data)
                .build().runToCompletion();
        
        List<Integer> sorted = data.stream().sorted().collect(toList());
        for (int i = 0; i < 16; i++) {
            assertEquals((int) sorted.get(i), finalState.getMemoryAt(i));
        }
        
    }
    
}
