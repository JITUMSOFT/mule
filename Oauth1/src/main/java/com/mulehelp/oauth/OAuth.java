package com.mulehelp.oauth;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import org.apache.http.client.ClientProtocolException;


import oauth.signpost.basic.custom.DefaultOAuthConsumer;
import oauth.signpost.custom.OAuthConsumer;
import oauth.signpost.exception.custom.OAuthCommunicationException;
import oauth.signpost.exception.custom.OAuthExpectationFailedException;
import oauth.signpost.exception.custom.OAuthMessageSignerException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//Use for local testing
public class OAuth {
	private static final String CONSUMER_KEY="CONSUMER_KEY";
	private static final String CONSUMER_SECRET="CONSUMER_SECRET";
	private static final String URL="URL";
	
	@SuppressWarnings({ "deprecation"})
	public static void main(String[] args)
			throws InvalidKeyException, NoSuchAlgorithmException, ClientProtocolException, IOException {
		OkHttpClient client = new OkHttpClient();
		OAuthConsumer defaultOAuthConsumer = new DefaultOAuthConsumer(CONSUMER_KEY
				,
				CONSUMER_SECRET);
		String url = URL;
		try {
			
			url=defaultOAuthConsumer.sign(url,true);
			
			

		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

		RequestBody body = RequestBody.create(mediaType,new File("#putfilewithrequest"));
		Request request = new Request.Builder().url(url).post(body).build();
		String oauth_consumer_key = request.url().queryParameter("oauth_consumer_key");
		String oauth_signature_method = request.url().queryParameter("oauth_signature_method");
		String oauth_timestamp = request.url().queryParameter("oauth_timestamp");
		String oauth_nonce = request.url().queryParameter("oauth_nonce");
		String oauth_signature = request.url().queryParameter("oauth_signature");

		StringBuilder Authorization = new StringBuilder();

		Authorization.append("OAuth " + "oauth_consumer_key=" + '"' + oauth_consumer_key + '"' + ","
				+ "oauth_signature_method=" + '"' + oauth_signature_method + '"' + "," + "oauth_timestamp=" + '"'
				+ oauth_timestamp + '"' + "," + "oauth_nonce=" + '"' + oauth_nonce + '"' + "," + "oauth_signature" + '"'
				+ oauth_signature + '"');
		try {
			Response response = client.newCall(request).execute();
			//System.out.println(response.body().bytes().toString());
			String out=new String(response.body().bytes());
			System.out.println(out);
			System.out.println(response.message());
			//System.out.println(IOUtils.toString(response.body().bytes()));
		} catch (IOException e) {

		}

	}
}