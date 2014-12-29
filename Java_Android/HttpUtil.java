
package pt.greatcinema.sellapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil{
    private HttpClient httpClient;
    
    public HttpUtil(HttpClient httpClient){
    	this.httpClient = httpClient;
    }
    public HttpUtil(){
    	this(new DefaultHttpClient());
    }
    
    public HttpResponse doGet(String url){
        try{
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            return httpResponse;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public HttpResponse doPost(String url, HttpEntity entity){
            HttpPost httpPost;
            try{
                httpPost = new HttpPost(url);
                httpPost.setEntity(entity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                return httpResponse;
            }catch(IOException e){
                throw new RuntimeException(e);
            }
    }
    
    public HttpResponse doPost(String url, List<NameValuePair> nameValuePairs){
        try{
            return doPost(url, new UrlEncodedFormEntity(nameValuePairs));
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }
    
    public static String getStringFromHttpResponse(HttpResponse httpResponse){
        try{
            String string = EntityUtils.toString(httpResponse.getEntity());
            return string;
        }catch(ParseException | IOException e){
            throw new RuntimeException(e);
        }
    }
}