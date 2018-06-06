package de.home24.home24task.networks;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import de.home24.home24task.pojo.BaseResponse;
import de.home24.home24task.utils.Constants;

public class NetworkManager {

    public static NetworkManager instance = null;

    public RequestQueue requestQueue;

    private NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    public static synchronized NetworkManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    //API method GET called based on the requests initiated by the fucntions
    public void get(final RequestResponseListener<Object> listener) {
        final GsonRequest<BaseResponse> request = new GsonRequest<>(Request.Method.GET,
                Constants.getBaseUrl(),
                BaseResponse.class,
                null,
                new Response.Listener<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse response) {
                        Gson gson = new Gson();
                        String obj = gson.toJson(response.getEmbedded().getArticles());
                        Log.e("NETWORK", "DATA: " + obj);
                        listener.onResult(Constants.WebApi.Response.SUCCESS, obj);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.getClass().equals(TimeoutError.class)) {
                            listener.onResult(Constants.WebApi.Response.TIMEOUT, null);
                        } else {
                            listener.onResult(Constants.WebApi.Response.NO_INTERNET, null);
                        }
                    }
                }
        );

        RetryPolicy policy = new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        request.setTag(Constants.getBaseUrl());
        requestQueue.add(request);
    }
}
