package org.service.util;

import org.junit.Assert;
import org.junit.Test;
import org.service.data.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PageUtilTest {

    private static String generateRandomString(Random random, int length){
        return random.ints(48,122)
                .filter(i-> (i<57 || i>65) && (i <90 || i>97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

  /*  @Test
    public void test(){
        IntStream.range(1,1000).forEach(page -> {
            testPagination(page);
        });
    }*/


@Test
public void testPagination(){
    List<String> input = new ArrayList<>();
    Random random = new Random();
    IntStream.range(0,1000).
            forEach(i -> input.add(generateRandomString(random, 9)));

    AtomicInteger failedCnt = new AtomicInteger(0);
    IntStream.range(1,10).forEach(page -> {
        try {
            Optional<String> pageNum = Optional.of(page + "");
            Optional<String> pageLimit = Optional.of("100");
            Result result = PageUtil.filterByPage(input, pageNum, pageLimit);
            Assert.assertTrue(result.getTotalpages() == 10);
            if (Integer.parseInt(pageNum.get()) <= 1) {
                Assert.assertTrue(result.getPrevious() == null);
            } else {
                Assert.assertTrue(result.getPrevious() != null);
            }
            if (Integer.parseInt(pageNum.get()) == 10) {
                Assert.assertTrue(result.getNext() == null);
            } else {
                Assert.assertTrue(result.getNext() != null);
            }
            Assert.assertTrue(result.getCount() == 100);
        }catch (AssertionError ae){
            failedCnt.incrementAndGet();
            ae.printStackTrace();
        }
    });
    if(failedCnt.get() > 0){
       throw new AssertionError("Some Tests Failed.."+failedCnt.get());
    }
}

}
