package com.itjenny.service.article;

import java.io.Serializable;

public class BookmarkPK implements Serializable {
    private static final long serialVersionUID = -3043169746607384796L;

    protected String userId;
    protected String title;

    public BookmarkPK() {
    }

    public BookmarkPK(String userId, String title) {
        this.userId = userId;
        this.title = title;
    }
}
