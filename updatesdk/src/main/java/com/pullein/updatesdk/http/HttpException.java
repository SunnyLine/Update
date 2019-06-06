package com.pullein.updatesdk.http;

/**
 * Update<br>
 * describe ：
 *
 * @author xugang
 * @date 2019/6/6
 */
public class HttpException extends Exception {

    private int errorCode;

    public HttpException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public HttpException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "HttpException{" +
                "errorCode=" + errorCode +
                "message=" + getMessage() +
                '}';
    }
}
