package com.pullein.updatesdk.http;

import android.text.TextUtils;

import com.pullein.common.utils.CollectionUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Update<br>
 * describe ：
 *
 * @author xugang
 * @date 2019/6/6
 */
public class UpdateHttp {
    private static ByteArrayOutputStream get(Request request) throws HttpException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
            connection.setRequestMethod(Request.GET);
            connection.setConnectTimeout(15 * 1000);
            Map<String, String> header = request.getHeader();
            if (!CollectionUtil.isEmpty(header)) {
                Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue())) {
                    }
                    connection.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (HttpStatus.HTTP_OK == connection.getResponseCode()) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[2048];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                baos.close();
                is.close();
                return baos;
            }
        } catch (MalformedURLException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        } catch (ProtocolException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        } catch (SocketTimeoutException e) {
            throw new HttpException(HttpException.Status.TIMEOUT_EXCEPTION);
        } catch (IOException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        }
        throw new HttpException(HttpException.Status.XXX_EXCEPTION);
    }

    private static ByteArrayOutputStream post(Request request) throws HttpException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(Request.POST);
            connection.setConnectTimeout(15 * 1000);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            addHeaders(connection, request);

            StringBuilder out = new StringBuilder();
            for (String key : request.body.keySet()) {
                if (out.length() != 0) {
                    out.append("&");
                }
                out.append(key).append("=").append(request.body.get(key));
            }
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(out.toString().getBytes());

            if (HttpStatus.HTTP_OK == connection.getResponseCode()) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[2048];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                baos.close();
                is.close();
                return baos;
            }
        } catch (MalformedURLException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        } catch (ProtocolException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        } catch (SocketTimeoutException e) {
            throw new HttpException(HttpException.Status.TIMEOUT_EXCEPTION);
        } catch (IOException e) {
            throw new HttpException(HttpException.Status.XXX_EXCEPTION);
        }
        throw new HttpException(HttpException.Status.XXX_EXCEPTION);
    }
}
