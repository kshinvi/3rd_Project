package com.emn.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class RestClient {
	
	private String rawBody;
	private ArrayList<NameValuePair> params;
	private ArrayList<NameValuePair> headers;
	{
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
	}
	
	private String url;
	private int responseCode;
	private String message;
	private String response;
	private byte[] rawDataBytes;
	
	/** 생성자 시작	 */
	public RestClient(){}
	public RestClient(String url){
		this.url = url;
	}
	public RestClient(String host, String port, String path){
		response = null;
		this.url = "http://" + host + ":" + port;
		if(path.substring(0,1).equals("/")){
			this.url += path;
		}else{
			this.url += "/" + path;
		}
	}
	/** 생성자 끝	 */
	
	public void addParam(String name, String value){
		params.add(new BasicNameValuePair(name, value));
	}
	
	public void addBody(String body){
		if(this.rawBody == null){
			this.rawBody="";
		}
		this.rawBody += body;
	}
	
	public void setBody(String body){
		this.rawBody = body;
	}
	
	public void addHeader(String name, String value){
		headers.add( new BasicNameValuePair(name, value) );
	}
	
	
	public void execute(RestMethod method) throws Exception{
		
		if(method == RestMethod.GET){						//get : read or select
			String combinedParams = "";
			if(!params.isEmpty()){
				combinedParams += "?";
				for (NameValuePair p : params){
					String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
					if(combinedParams.length() > 1){
						combinedParams += "&" + paramString;
					}else{
						combinedParams += paramString;
					}
				}
			}
			HttpGet request = new HttpGet(url + combinedParams);
			for(NameValuePair h : headers){
				request.addHeader(h.getName(), h.getValue());
			}			
			executeRequest(request, url);
			
		}else if(method == RestMethod.POST){				//post : insert or create
			
			HttpPost request = new HttpPost(url);
			
			for (NameValuePair h : headers) {
				request.addHeader(h.getName() , h.getValue());
			}
			if(!params.isEmpty()){
				request.setEntity( new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}
			executeRequest(request, url);
		
		}else if(method == RestMethod.PUT){					//put : update or alter
			HttpPut request = new HttpPut(url);
			for(NameValuePair h : headers){
				request.addHeader(h.getName() , h.getValue());
			}
			if(!params.isEmpty()){
				String en = "";
				for(NameValuePair p : params){
					en += p.getValue();
				}
				StringEntity input = new StringEntity(en);
				request.setEntity(input);
			}
			if(rawBody != null){
				request.setEntity(new StringEntity(rawBody));
			}
			executeRequest(request, url);
			
		}else if (method == RestMethod.DELETE){				//delete : delete
			String combinedParams = "";
			if(!params.isEmpty()){
				combinedParams += "?";
				for (NameValuePair p : params){
					String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
					if(combinedParams.length() > 1){
						combinedParams += "&" + paramString;
					}else{
						combinedParams += paramString;
					}
				}
			}
			HttpDelete request = new HttpDelete(url + combinedParams);
			for(NameValuePair h : headers){
				request.addHeader(h.getName(), h.getValue());
			}			
			executeRequest(request, url);
		}
		
	}
	
	/**
	 * executeRequest
	 * @param request
	 * @param url
	 */
	private void executeRequest(HttpUriRequest request, String url){
		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse;
		try{
			httpResponse = client.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();
			HttpEntity entity = httpResponse.getEntity();
			if(entity != null){
				InputStream instream = entity.getContent();
				response = convertStreamToString(instream);
				response = response.replaceAll("&", "&amp;");
				instream.close();
			}
		}catch(ClientProtocolException e){
			client.getConnectionManager().shutdown();
		}catch(IOException e){
			client.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * convertStreamToString
	 * @param is
	 * @return
	 */
	private static String convertStreamToString (InputStream is){
		BufferedReader reader = new BufferedReader( new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while( (line = reader.readLine()) != null){
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
		
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public byte[] getRawDataBytes() {
		return rawDataBytes;
	}
	public void setRawDataBytes(byte[] rawDataBytes) {
		this.rawDataBytes = rawDataBytes;
	}
	
}
