package com.itjenny.support;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.itjenny.common.CommonTest;

public class URLTest extends CommonTest {
    @Test
    public void makeAbsolutePath() {
        // Given
        String result = URL.makeAbsolutePath(URL.FORM, URL.ARTICLE);
        assertThat(result, is("/" + URL.FORM + "/" + URL.ARTICLE));
    }
}