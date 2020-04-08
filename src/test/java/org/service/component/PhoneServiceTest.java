package org.service.component;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.service.config.Config;
import org.service.data.Result;
import org.service.exception.InvalidPhoneNumException;

import java.util.Arrays;
import java.util.Optional;

public class PhoneServiceTest {

    PhoneService phoneService;
    String phoneNum;
    @Mock
    Config config;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        phoneService = new PhoneService();
        phoneService.config = config;
        org.mockito.Mockito.when(config.get(" ")).thenReturn(Arrays.asList("-"));
        org.mockito.Mockito.when(config.get("-")).thenReturn(Arrays.asList("-"));
        org.mockito.Mockito.when(config.get("0")).thenReturn(Arrays.asList("0"));
        org.mockito.Mockito.when(config.get("1")).thenReturn(Arrays.asList("1"));
        org.mockito.Mockito.when(config.get("2")).thenReturn(Arrays.asList("a","b","c"));
        org.mockito.Mockito.when(config.get("3")).thenReturn(Arrays.asList("d","e","f"));
        org.mockito.Mockito.when(config.get("4")).thenReturn(Arrays.asList("g","h","i"));
        org.mockito.Mockito.when(config.get("5")).thenReturn(Arrays.asList("j","k","l"));
        org.mockito.Mockito.when(config.get("6")).thenReturn(Arrays.asList("m","n","o"));
        org.mockito.Mockito.when(config.get("7")).thenReturn(Arrays.asList("p","q","r","s"));
        org.mockito.Mockito.when(config.get("8")).thenReturn(Arrays.asList("t","u","v"));
        org.mockito.Mockito.when(config.get("9")).thenReturn(Arrays.asList("w","x","y","z"));
        phoneNum = "";
    }

    @Test(expected = InvalidPhoneNumException.class)
    public void testInvalidPhoneException(){
        phoneService.getAlphanumericNumbers(phoneNum,Optional.of(""),Optional.of(""));
    }

    @Test
    public void testAlphaNumeric(){
        phoneNum = "123 456 7890";
        Result result = phoneService.getAlphanumericNumbers(phoneNum,Optional.of("1"),Optional.of("100"));
        Assert.assertTrue(result.getData().size() == 100);
    }
}
