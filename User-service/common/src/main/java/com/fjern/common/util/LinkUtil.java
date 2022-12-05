package com.fjern.common.util;

public class LinkUtil {

    public static final String REL_COLLECTION = "collection";

    public static final String REL_NEXT = "next";

    public static final String REL_PREV = "prev";

    public static final String REL_FIRST = "first";

    public static final String REL_LAST = "last";

    public static String createLinkHeader(String uri, String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";

    }
}
