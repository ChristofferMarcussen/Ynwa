package com.bekk.Faggruppe.http;

import com.bekk.Faggruppe.domain.Result;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Calculator {

    public static Result subtract(String firstNumber, String secondNumber) {
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.calcatraz.com/calculator/api?c="+firstNumber+"-"+secondNumber;
            HttpGet get = new HttpGet(url);
            String result = EntityUtils.toString(httpclient.execute(get)
                    .getEntity(), "UTF-8");

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Result add(String firstNumber, String secondNumber) {
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.calcatraz.com/calculator/api?c="+firstNumber+"%2B"+secondNumber;
            HttpGet get = new HttpGet(url);
            String result = EntityUtils.toString(httpclient.execute(get)
                    .getEntity(), "UTF-8");

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
