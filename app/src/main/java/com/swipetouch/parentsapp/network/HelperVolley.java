package com.swipetouch.parentsapp.network;

/**
 * Created by sjena on 12/10/2016.
 */

import android.content.Context;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.swipetouch.parentsapp.util.AppController;
import com.swipetouch.parentsapp.util.UiUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HelperVolley<T> {

  /*  static HelperVolley helperNetwork;

    private static Context mContext;

    private HelperVolley() {
    }

    public HelperVolley(Context context) {
        mContext = context;
    }

    public static HelperVolley getInstance(Context context) {
        mContext = context;
        if (helperNetwork == null)
            helperNetwork = new HelperVolley();
        return helperNetwork;
    }

    *//**
     * Function to create the URI between QA and PROD environments
     *
     * @param ApiServiceName
     * @return URI with scheme, host,path, port
     *//*
*//*    public static URI makeURI(String ApiServiceName, int port, String apiHost) {
        URI uri = null;
//        int port = Constants.API_PORT;
        try {
            if (port != -1) {
                if(port == Constants.QA_PORT) {
                    uri = new URI(Constants.API_SCHEME, apiHost, ApiServiceName, null);
                } else {
                    uri = new URI(Constants.API_SCHEME, null, apiHost, port,
                            ApiServiceName, null, null);
                }
            } else {
                uri = new URI(Constants.API_SCHEME_PROD, apiHost, ApiServiceName, null);
            }
            if (port == 443) {
                uri = new URI(Constants.SECURE_API_SCHEME, apiHost, ApiServiceName, null);
            }

        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        return uri;
    }*//*

    *//**
     * This function will always mark calls as POST except when written case for
     * GET calls // 1- post 0-get
     *
     * @param
     * @return
     *//*
    public static int methodCallType(int api) {
        int method = api;
        *//*switch (api) {
            case Constants.VIEW_ORDER_HISTORY_ITEM_DETAILS:
            case Constants.GET_BUYER_PRODUCT_PREF:
            case Constants.VIEW_ORDERS:
            case Constants.VIEW_SUBSTITUTION_PENDING_ITEMS:
            case Constants.RUNNER_CHAT_GET_CHAT_MESSAGE:
            case Constants.PUSH_PREFERENCE_GET:
            case Constants.VIEW_USER_COUPONS:

                method = 0;
                break;

            default:
                method = 1;
                break;
        }*//*
        return method;
    }

    public static <T> void CallAPIForGet(Context context, String url,
                                         Listener<T> listener, Type repClass, String cancel, Response.ErrorListener errorListener) {

        GsonRequest<T> myReq = null;


        myReq = new GsonRequest<T>(0, url,
                repClass, null, listener, errorListener);


        RequestQueue mRequestQueue = ((BobApplication) context.getApplicationContext()).getQueue();
        // String tag = getTag(context);


        myReq.setTag(cancel);
        mRequestQueue.add(myReq);


    }


    public static void cancelQueueRequest(final Context context) {
        ((BobApplication) context.getApplicationContext()).getQueue().cancelAll(
                new RequestQueue.RequestFilter() {
                    @Override
                    public boolean apply(Request<?> request) {

                        //String tag = getTag(context);
                        if (request.getTag() == null)
                            return true;

                        else if (((String) request.getTag()).equalsIgnoreCase("DontCancel")) {
                            return false;
                        } else
                            return true;

                    }
                });

    }


    public static <T> void CallApiWithJson(Context context, String url, String API, Listener<T> listener,
                                           final String json, Type repClass, Response.ErrorListener errorListener) {

        if (json != null) {
            UiUtil.showLog("HelperVolley:CallApiWithJson", json);
        }

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(1), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    if (json != null)
                        return json.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }
                if (LocalManager.getInstance() != null && LocalManager.getInstance().getToken() != null)
                    headers.put("token", LocalManager.getInstance().getToken());
                headers.put("AppVersion", Constants.VERSION_IDENTIFIER);
                UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((BobApplication) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }

    public static <T> void CallPostApi(Context context, String url, String API, Listener<T> listener, Type repClass,
                                       Response.ErrorListener errorListener) {

        String newUrl = url.replaceAll("%3F", "?");

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(1), newUrl,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }
                if (LocalManager.getInstance() != null && LocalManager.getInstance().getToken() != null)
                    headers.put("token", LocalManager.getInstance().getToken());
                headers.put("AppVersion", Constants.VERSION_IDENTIFIER);
                UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((BobApplication) context.getApplicationContext()).getQueue();
        mRequestQueue.add(myReq);

    }

    public static <T> void CallGetApi(Context context, String url, String API, Listener<T> listener, Type repClass,
                                      Response.ErrorListener errorListener) {

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(0), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }
                if (LocalManager.getInstance() != null && LocalManager.getInstance().getToken() != null) {
                    headers.put("token", LocalManager.getInstance().getToken());
                    UiUtil.showLog("TOKEN IN HEADER===================>>", LocalManager.getInstance().getToken());
                }
                headers.put("AppVersion", Constants.VERSION_IDENTIFIER);
                UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((BobApplication) context.getApplicationContext()).getQueue();
        mRequestQueue.add(myReq);

    }*/

    public static <T> void CallApiWithJson(Context context, String url, String API, Listener<T> listener,
                                           final String json, Type repClass, Response.ErrorListener errorListener ) {

        if (json != null) {
            UiUtil.showLog("HelperVolley:CallApiWithJson", json);
        }

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(1), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    if (json != null)
                        return json.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }


                headers.put("Accept", "application/json");
                //headers.put("Content-Type", "application/json; charset=utf-8");

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }


    public static <T> void CallApiWithJson(Context context, String url, String API, Listener<T> listener,
                                           final String json, Type repClass, Response.ErrorListener errorListener, final String token ) {

        if (json != null) {
            UiUtil.showLog("HelperVolley:CallApiWithJson", json);
        }

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(1), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    if (json != null)
                        return json.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }


                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Bearer "+token);

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }

    public static <T> void CallApiWithJsonWithOutToken(Context context, String url, String API, Listener<T> listener,
                                                       final String json, Type repClass, Response.ErrorListener errorListener) {

        if (json != null) {
            // UiUtil.showLog("HelperVolley:CallApiWithJson", json);
        }

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(1), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    if (json != null)
                        return json.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }


                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                headers.put("XSRF-TOKEN", "1d21797e-5229-4ee2-9690-9d58a6156c30");

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }




    public static <T> void CallGetApi(Context context, String url, String API, Listener<T> listener, Type repClass,
                                      Response.ErrorListener errorListener) {

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(0), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }



                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("XSRF-TOKEN", "1d21797e-5229-4ee2-9690-9d58a6156c30");

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }


    public static <T> void CallGetApi(Context context, String url, String API, Listener<T> listener, Type repClass,
                                      Response.ErrorListener errorListener, final String token) {

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(0), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }



                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=utf-8");
                //headers.put("Authorization", "Bearer "+token);

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }




    public static <T> void CallGetApiTogetClassList(Context context, String url, String API, Listener<T> listener, Type repClass,
                                                    Response.ErrorListener errorListener) {

        GsonRequest<T> myReq = new GsonRequest<T>(
                HelperVolley.methodCallType(0), url,
                repClass, null, listener, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                if (headers == null || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }



                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("XSRF-TOKEN", "1d21797e-5229-4ee2-9690-9d58a6156c30");

                //UiUtil.showLog("Volley","VI"+Constants.VERSION_IDENTIFIER);
                return headers;
            }
        };

        RequestQueue mRequestQueue = ((AppController) context.getApplicationContext()).getQueue();
        myReq.setTag(API);
        mRequestQueue.add(myReq);

    }



    public static int methodCallType(int api) {
        int method = api;
        /*switch (api) {
            case Constants.VIEW_ORDER_HISTORY_ITEM_DETAILS:
            case Constants.GET_BUYER_PRODUCT_PREF:
            case Constants.VIEW_ORDERS:
            case Constants.VIEW_SUBSTITUTION_PENDING_ITEMS:
            case Constants.RUNNER_CHAT_GET_CHAT_MESSAGE:
            case Constants.PUSH_PREFERENCE_GET:
            case Constants.VIEW_USER_COUPONS:

                method = 0;
                break;

            default:
                method = 1;
                break;
        }*/
        return method;
    }
}
