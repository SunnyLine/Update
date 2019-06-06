package com.pullein.updatesdk.http;

import java.io.InputStream;

/**
 * Update<br>
 * describe ：
 *
 * @author xugang
 * @date 2019/6/6
 */
public interface CallBack {
    void onSuccess(InputStream is);
    void onFailure(Exception e);
}
