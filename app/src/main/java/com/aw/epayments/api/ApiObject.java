package com.aw.epayments.api;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * Created by alexboey on 1/25/17.
 */

public class ApiObject {
    // public static String URL = "http://41.72.203.234:8099/Api/api/Msacco/";
    public static  String URL = "http://41.72.203.234:35051/MsaccoPlus/api/Msacco/";

    public static String GenerateToken ="GenerateToken";
    public static String password;
    private static String results;

    public static String getVolley(final Context context , String function , String jsonRequest , final VolleyCallback callback)
    {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = ApiObject.URL+function;
            Log.i("URL",url);
            Log.i("REQUEST",jsonRequest);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("jj","jj");
     /*       jsonBody.put("authorization_credentials", "Android Volley Demo");
            jsonBody.put("api_key", "BNK");
            jsonBody.put("token",);
            jsonBody.put("corporate_no",);
            jsonBody.put("msisdn",);
            jsonBody.put("account_number",)*/
            final String requestBody = jsonRequest;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                 Log.i("RESPONSE",response);

                    try {
                        JSONObject object = new JSONObject(response);
                        if(!object.getBoolean("is_successful")){
                            if (object.getString("error").equalsIgnoreCase("Invalid Token Supplied")){
                              //  Util.alertMasseage(context,"Session Expired","You need to close the app and login again.");
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    results = response;
                    callback.onSuccess(results);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                    }
                    return requestBody.getBytes();
                }

            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    600000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }

    public interface VolleyCallback{
        void onSuccess(String result);
    }

}
