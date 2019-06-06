package com.pullein.updatesdk.http;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Update<br>
 * describe ：
 *
 * @author xugang
 * @date 2019/6/6
 */
public class Request {
    public static final String GET = "GET";
    public static final String POST = "POST";

    private String url;
    private Map<String, String> body;
    private Map<String, String> header;
    private @RequestMethod
    String requestMethod;
    private long timeout;
    private CallBack callBack;

    public Request(Build build) {
        this.url = build.url;
        this.body = build.body;
        this.header = build.header;
        this.requestMethod = build.requestMethod;
        this.timeout = build.timeout;
        this.callBack = build.callBack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    class Build {
        String url;
        Map<String, String> body;
        Map<String, String> header;
        @RequestMethod
        String requestMethod;
        long timeout;
        private CallBack callBack;

        public Build(String url) {
            this.url = url;
        }

        public Build setBody(Map<String, String> body) {
            this.body = body;
            return this;
        }

        public Build addBody(String key, String value) {
            if (body == null) {
                body = new HashMap<>();
            }
            body.put(key, value);
            return this;
        }

        public Build setHeader(Map<String, String> header) {
            this.header = header;
            return this;
        }

        public Build addHeader(String key, String value) {
            if (header == null) {
                header = new HashMap<>();
            }
            header.put(key, value);
            return this;
        }

        public Build setRequestMethod(@RequestMethod String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Build setTimeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Build setCallBack(CallBack callBack) {
            this.callBack = callBack;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    @StringDef({GET, POST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestMethod {
    }
}
