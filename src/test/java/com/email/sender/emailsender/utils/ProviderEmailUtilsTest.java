package com.email.sender.emailsender.utils;

import com.email.sender.emailsender.models.EmailInit;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProviderEmailUtilsTest {
    ProviderEmailUtils providerEmailUtils = new ProviderEmailUtils();

    @Test
    public void testInitNullResult() throws Exception {
        EmailInit result = providerEmailUtils.init(null, null, null);
        assertNull(result);
    }

    @Test
    public void testInit() throws Exception {
        String account = "account";
        String password = "password";
        String endPointUrl = "endPointUrl";
        EmailInit result = providerEmailUtils.init(account, password, endPointUrl);
        assertEquals(account, result.getAccount());
        assertEquals(password, result.getPassword());
        assertEquals(endPointUrl, result.getEndPointUrl());
    }

    @Test
    public void testSend() throws Exception {
        boolean result = providerEmailUtils.send(Arrays.<String>asList("String"), Arrays.<String>asList("String"), "subject", "content");
        assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme