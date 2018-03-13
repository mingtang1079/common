package com.appbaselib.utils;

import java.util.Locale;

/**
 * Description: 用于检查url
 * Created by lbw on 2017/4/13.
 */

public class HttpUtil {

    public static boolean containsHttp(String uri) {
        switch (Scheme.ofUri(uri)) {
            case HTTP:
            case HTTPS:
                return true;
            case UNKNOWN:
            default:
                return false;
        }
    }

    public static boolean containsHttpAndFile(String uri) {
        switch (Scheme.ofUri(uri)) {
            case HTTP:
            case HTTPS:
            case FILE:
                return true;
            case UNKNOWN:
            default:
                return false;
        }
    }


    public static boolean containsFile(String uri) {
        switch (Scheme.ofUri(uri)) {
            case FILE:
                return true;
            case UNKNOWN:
            default:
                return false;
        }
    }

    public Scheme ofScheme(String uri) {
        return Scheme.ofUri(uri);
    }

    public static String convertToHttp(String url) {
        if (url.startsWith("https")) {
            url = url.replaceFirst("https", "http");
        } else if (url.startsWith("HTTPS")) {
            url = url.replaceFirst("HTTPS", "HTTP");
        }
        return url;
    }

    public enum Scheme {
        HTTP("http"), HTTPS("https"), FILE("file"), CONTENT("content"), ASSETS("assets"), DRAWABLE("drawable"), UNKNOWN("");

        private String scheme;
        private String uriPrefix;

        Scheme(String scheme) {
            this.scheme = scheme;
            uriPrefix = scheme + "://";
        }

        /**
         * Defines scheme of incoming URI
         *
         * @param uri URI for scheme detection
         * @return Scheme of incoming URI
         */
        public static Scheme ofUri(String uri) {
            if (uri != null) {
                for (Scheme s : values()) {
                    if (s.belongsTo(uri)) {
                        return s;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean belongsTo(String uri) {
            return uri.toLowerCase(Locale.US).startsWith(uriPrefix);
        }

        /** Appends scheme to incoming path */
        public String wrap(String path) {
            return uriPrefix + path;
        }

        /** Removed scheme part ("scheme://") from incoming URI */
        public String crop(String uri) {
            if (!belongsTo(uri)) {
                throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", uri, scheme));
            }
            return uri.substring(uriPrefix.length());
        }
    }
}
