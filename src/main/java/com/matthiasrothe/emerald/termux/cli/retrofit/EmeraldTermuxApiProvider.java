package com.matthiasrothe.emerald.termux.cli.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class EmeraldTermuxApiProvider {
	private static EmeraldTermuxApi api;
	
	private EmeraldTermuxApiProvider() {}
	
	public static EmeraldTermuxApi provideApi(final String domain, final int port) {
		if (api == null) {
			buildApi(domain, port);
		}
		
		return api;
	}
	
	private static void buildApi(final String domain, final int port) {
		OkHttpClient client = new OkHttpClient.Builder().build();
		
		Retrofit retrofit = new Retrofit.Builder().
				baseUrl("http://" + domain + ":" + port + "/emerald-termux/api/").
				addConverterFactory(JetroConverterFactory.create()).
				client(client).
				build();
		
		api = retrofit.create(EmeraldTermuxApi.class);
	}
}
