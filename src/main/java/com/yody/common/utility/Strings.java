package com.yody.common.utility;

import com.yody.common.enums.SearchOperation;

public final class Strings {

    public static String trim(final String s) {
        return ((s == null) ? null : s.trim());
    }

    public static String trimToNull(String s) {
        s = trim(s);
        return isEmpty(s) ? null : s;
    }

    public static String trimToEmpty(String s) {
        if (s == null) {
            return "";
        }
        return s.trim();
    }

    public static String trim(String s, int max) {
        if (s == null) {
            return null;
        } else {
            s = s.trim();
        }
        if (s.length() > max) {
            s = s.substring(0, max);
        }
        return s;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


}
