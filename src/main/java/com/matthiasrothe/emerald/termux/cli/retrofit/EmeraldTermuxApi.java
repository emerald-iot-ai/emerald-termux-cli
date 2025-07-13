package com.matthiasrothe.emerald.termux.cli.retrofit;

import net.sf.jetro.tree.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmeraldTermuxApi {

	@GET("status")
	Call<JsonObject> getFullStatus();
	
	@POST("status")
	Call<JsonObject> getSelectedStatus(@Body JsonObject body);
	
	@POST("pause")
	Call<JsonObject> pause();
	
	@POST("resume")
	Call<JsonObject> resume();
	
	@POST("shutdown")
	Call<JsonObject> shutdown();
}
