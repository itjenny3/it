package com.itjenny.support;

import org.apache.commons.lang.ArrayUtils;

import com.google.common.base.Joiner;

public class URL {
    public static final String MAIN = "";
    public static final String ARTICLE = "article";
    public static final String FORM = "form";
    public static final String NEW = "new";
    public static final String WRONG = "wrong";

    public static final String SEARCH = "search";
    public static final String SETTING = "setting";

    public static String makeAbsolutePath(String... path) {
    	String[] prefix = new String[] {""};
        return Joiner.on("/").join(ArrayUtils.addAll(prefix,  path));
    }

    public static String makeRelativePath(String... path) {
        return Joiner.on("/").join(path);
    }
}