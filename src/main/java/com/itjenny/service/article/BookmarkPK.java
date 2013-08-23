package com.itjenny.service.article;

import java.io.Serializable;
		
public class BookmarkPK implements Serializable {
    protected String providerUserId;
    protected String title;

    public BookmarkPK() {}

    public BookmarkPK(String providerUserId, String title) {
        this.providerUserId = providerUserId;
        this.title = title;
    }
}
