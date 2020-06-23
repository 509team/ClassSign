package com.fzn.classsign.asynctask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomAsyncTask<T> extends AsyncTask<Object, Void, String> {
    /**
     * 请求头
     */
    private Map<String, String> requestHeaders;
    /**
     * 请求体
     */
    private Map<String, String> requestBody;
    /**
     * 请求参数，以&key=value的形式添加在url后
     */
    private Map<String, String> requestParams;

    private Method requestMethod = null;

    private String requestUrl;


    enum Method {
        REQUEST_METHOD_GET,
        REQUEST_METHOD_POST,
        REQUEST_METHOD_PATCH,
        REQUEST_METHOD_PUT,
        REQUEST_METHOD_DELETE
    }


    public CustomAsyncTask(Map<String, String> headers, Map<String, String> body, Map<String, String> params, String url) {
        this.requestHeaders = headers;
        this.requestBody = body;
        this.requestParams = params;
        this.requestUrl = url;
    }

    public CustomAsyncTask<T> gett() {
        this.requestMethod = Method.REQUEST_METHOD_GET;
        return this;
    }

    public CustomAsyncTask<T> post() {
        this.requestMethod = Method.REQUEST_METHOD_POST;
        return this;
    }

    public CustomAsyncTask<T> patch() {
        this.requestMethod = Method.REQUEST_METHOD_PATCH;
        return this;
    }

    public CustomAsyncTask<T> put() {
        this.requestMethod = Method.REQUEST_METHOD_PUT;
        return this;
    }

    public CustomAsyncTask<T> delete() {
        this.requestMethod = Method.REQUEST_METHOD_DELETE;
        return this;
    }


    /**
     * ts的参数将依次呗添加在url后面，并用/隔开
     *
     * @param os restful的get参数
     * @return 请求结果
     */
    @Override
    protected String doInBackground(Object... os) {
        if (requestMethod == null) {
            return null;
        }
        //初始化参数
        Headers headers = null;
        FormBody formBody = null;
        String params = null;

        //请求头装载
        if (requestHeaders != null) {
            Headers.Builder headersBuilder = new Headers.Builder();
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                headersBuilder.add(entry.getKey(), entry.getValue());
            }
            headers = headersBuilder.build();
        }

        //请求体装载
        if (requestBody != null) {
            if (requestMethod != Method.REQUEST_METHOD_GET) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : requestBody.entrySet()) {
                    bodyBuilder.add(entry.getKey(), entry.getValue());
                }
                formBody = bodyBuilder.build();
            }
        }


        //请求参数生成
        if (requestParams != null) {
            StringBuilder paramsBuilder = new StringBuilder();
            if (requestParams.size() != 0) {
                paramsBuilder.append("?");
                for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                    paramsBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
                }
                paramsBuilder.deleteCharAt(paramsBuilder.length() - 1);
                params = paramsBuilder.toString();
            }
        }


        //构造器初始化
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null) {
            requestBuilder.headers(headers);
        }
        if (formBody != null) {
            if (requestMethod == Method.REQUEST_METHOD_POST) {
                requestBuilder.post(formBody);
            }
            if (requestMethod == Method.REQUEST_METHOD_PATCH) {
                requestBuilder.patch(formBody);
            }
            if (requestMethod == Method.REQUEST_METHOD_PUT) {
                requestBuilder.put(formBody);
            }
            if (requestMethod == Method.REQUEST_METHOD_DELETE) {
                requestBuilder.delete(formBody);
            }
        }

        //装载参数
        if (params != null) {
            requestUrl += params;
        }

        //get方法 restful风格参数处理
        if (requestMethod == Method.REQUEST_METHOD_GET) {
            requestBuilder.get();
            if ("/".equals(requestUrl.charAt(requestUrl.length() - 1))) {
                requestUrl.substring(0, requestUrl.length() - 1);
            }
            for (Object o : os) {
                requestUrl += ("/" + o.toString());
            }
        }

        //装载url
        requestBuilder.url(requestUrl);


        Request request = requestBuilder.build();

        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String body = response.body().string();
                return body;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ResponseResultJson<T> getResponse(String body) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<ResponseResultJson<T>>() {
        }.getType();
        ResponseResultJson<T> newsListResponse = gson.fromJson(body, type);
        return newsListResponse;
    }

    class ResponseResultJson<T> implements Serializable {

        /**
         * 服务返回状态
         */
        private int code;
        /**
         * 详细状态
         */
        private int statusCode;
        /**
         * 返回状态描述
         */
        private String msg;
        /**
         * 附加信息描述
         */
        private String addtionalInfo;
        /**
         * 返回数据
         */
        @SerializedName("newslist")
        private T data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getAddtionalInfo() {
            return addtionalInfo;
        }

        public void setAddtionalInfo(String addtionalInfo) {
            this.addtionalInfo = addtionalInfo;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
