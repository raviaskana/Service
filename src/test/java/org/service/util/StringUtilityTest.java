package org.service.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilityTest {

    @Test
    public void tryParse(){
        Assert.assertTrue(StringUtility.tryParse("5",1) == 5);
        Assert.assertTrue(StringUtility.tryParse("5fd",1) == 1);
    }
}
