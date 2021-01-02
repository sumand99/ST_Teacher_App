package com.swipetouch.parentsapp.network;



import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.swipetouch.parentsapp.util.Config;
import com.swipetouch.parentsapp.util.UiUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Volley adapter for JSON requests with POST method that will be parsed into
 * Java objects by Gson.
 */

/**
 * @author gour
 */
public class GsonRequest<T> extends Request<T> {
    private Gson mGson = new Gson();

    private Class<T> clazz;

    private Type type;

    private Map<String, String> headers;

    private Map<String, String> params;

    private Listener<T> listener;

    private ErrorListener eListener;


    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url   URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     */
    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.clazz = clazz;
        this.listener = listener;
        this.eListener = errorListener;
        UiUtil.showLog("HelperVolley", "========================== request " + url);
    }



    /**
     * @param method        this should be post
     * @param url           - service call with all the parameters
     * @param clazz         - gson output class, Relevant class object, for Gson's
     *                      reflection
     * @param params        - parameters to be send with call
     * @param listener      - response listener call back
     * @param errorListener - if error occurs this listener is executed
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> params,
                       Listener<T> listener, ErrorListener errorListener) {

        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.clazz = clazz;
        this.params = params;
        this.listener = listener;
        this.headers = null;
        this.eListener = errorListener;

        UiUtil.showLog("HelperVolley", "========================== request " + url + params);
    }

    public GsonRequest(int method, String url, Type clazz, Map<String, String> params,
                       Listener<T> listener, ErrorListener errorListener) {

        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.clazz = null;
        this.type = clazz;
        this.params = params;
        this.listener = listener;
        this.eListener = errorListener;
        this.headers = null;

        UiUtil.showLog("HelperVolley", "========================== request " + url + params);

    }

    public GsonRequest(int method, String url, Type clazz, Listener<T> listener, ErrorListener errorListener) {

        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.clazz = null;
        this.type = clazz;
        this.listener = listener;
        this.eListener = errorListener;
        this.headers = null;

        UiUtil.showLog("HelperVolley", "========================== request " + url +" | Parameters ->"+ params);

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected void deliverResponse(T response) {
        if(listener != null)
            listener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        eListener.onErrorResponse(error);
    }

    @SuppressWarnings("unchecked")
   /* @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            UiUtil.showLog("HelperVolley", "========================== received response" + json);
            if (json.length() > 0) {
                if (clazz != null)
                    return Response.success(mGson.fromJson(json, clazz),
                            HttpHeaderParser.parseCacheHeaders(response));
                else
                    return (Response<T>) Response.success(mGson.fromJson(new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers)), this.type),
                            HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new VolleyError("no response from servers"));
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }

    }*/

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
         ByteArrayInputStream isr =null;
        InputStreamReader isReader =null;
        try {

            // String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //String json = new String(response.data);

            String temp = new String(response.data);
            /*if(Constants.IS_SEARCH_ONLY) {
                String jsonString = "{\"searchResult\":" + temp + "}";
                byte[] b = jsonString.getBytes();
                isr = new ByteArrayInputStream(b);
            } else {
                isr = new ByteArrayInputStream(response.data);
            }*/
            isr = new ByteArrayInputStream(response.data);


            isReader = new InputStreamReader(isr);
            JsonReader reader = new JsonReader(isReader);
            /*final EntMyClass entMyClass = GSON.fromJson(reader, EntMyClass.class);
            reader.close();
            c.disconnect();*/
//            UiUtil.cancelProgressDialog();

            if (reader!=null) {
                if(Config.DEBUG){
                    UiUtil.showLog("GsonRequest", "========================== received response" + new String(response.data));
                }
                if (clazz != null) {
                    return (Response<T>) Response.success(mGson.fromJson(reader, clazz),
                            HttpHeaderParser.parseCacheHeaders(response));
                }
                else{
                    return (Response<T>) Response.success(mGson.fromJson(reader, this.type), HttpHeaderParser.parseCacheHeaders(response));
                //    return (Response<T>) Response.success(mGson.fromJson(new String(b,HttpHeaderParser.parseCharset(response.headers)), this.type),HttpHeaderParser.parseCacheHeaders(response));
                }
            } else {
                return Response.error(new VolleyError("no reseponse from servers"));
            }

        } /*catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }*/ catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
        finally {

                try {
                    if(isr != null)
                    isr.close();
                    if(isReader !=null)
                        isReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
