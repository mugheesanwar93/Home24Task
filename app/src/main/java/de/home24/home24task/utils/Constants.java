package de.home24.home24task.utils;

public class Constants {

    public static String url = "https://api-mobile.home24.com/api/v2.0/categories/100/articles?appDomain=1&locale=de_DE&limit=10";

    public static String getBaseUrl() {
        return url;
    }

    public static class WebApi { //web responses to identify the response from the server
        public static class Response {
            public static final int SUCCESS = 200;
            public static final int NO_INTERNET = 504;
            public static final int TIMEOUT = 408;
        }
    }
}
