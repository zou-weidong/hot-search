package com.zwd.searches.bean;

import com.alibaba.fastjson2.annotation.JSONField;

public class HotSearch {
    @JSONField(name = "rich_text")
    private String context;
    @JSONField(name = "update_time")
    private String updateTime;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "<div><p>" + updateTime + " " + context.trim() + "</p></div>";
    }
}
