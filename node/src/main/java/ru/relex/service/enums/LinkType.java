package ru.relex.service.enums;

public enum LinkType {

    GET_DOC("file/get-doc"),
    GET_PHOTO("file/get-photo");

    private final String link;

    LinkType(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return link;
    }
}
