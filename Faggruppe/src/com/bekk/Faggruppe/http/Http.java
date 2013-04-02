package com.bekk.Faggruppe.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Http {

    public Response subtract(String firstNumber, String secondNumber) {
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.calcatraz.com/calculator/api?c="+firstNumber+"-"+secondNumber;
            HttpGet get = new HttpGet(url);

            return new Response(httpclient.execute(get));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Response add(String firstNumber, String secondNumber) {
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.calcatraz.com/calculator/api?c="+firstNumber+"%2B"+secondNumber;
            HttpGet get = new HttpGet(url);

            return new Response(httpclient.execute(get));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        return out.toString();
    }

    public static class Response {
        private InputStream responseBody;

        public Response(HttpResponse httpResponse) throws IOException {
            responseBody = httpResponse.getEntity().getContent();
        }

        public InputStream getResponseBody() {
            return responseBody;
        }
    }
}
