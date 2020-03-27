package com.baiheng.utilsdemo;

public class GaidInfo {

    private String mId;
    private boolean mIdLimeted;

    public GaidInfo(String id, boolean idLimeted) {
        this.mId = id;
        this.mIdLimeted = idLimeted;
    }

    public String getId() {
        return mId;
    }

    public boolean getIdLimeted() {
        return mIdLimeted;
    }
}
