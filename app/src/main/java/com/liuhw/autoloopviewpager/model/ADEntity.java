package com.liuhw.autoloopviewpager.model;

/**
 * Created by gary on 17-2-27.
 */

public class ADEntity {
    public enum ADType {
        PIC,
        VIDEO
    }

    private String url;
    private ADType type;
    private String name;

    public ADEntity(String url, ADType type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ADType getType() {
        return type;
    }

    public void setType(ADType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ADEntity{" +
                "url='" + url + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
