package com.matthiasrothe.emerald.termux.cli.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Objects;

import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApi;
import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApiProvider;

import net.sf.jetro.tree.JsonObject;
import net.sf.jetro.tree.builder.JsonTreeBuilder;
import retrofit2.Call;
import retrofit2.Response;

public class StatusCommand implements Command {
	private final JsonTreeBuilder builder = new JsonTreeBuilder();
	
	private final JsonObject requestBody;
	private final String domain;
	private final int port;
	
	public StatusCommand(final String requestBodyFile, final String domain, final int port) throws Exception {
		Objects.requireNonNull(domain, "domain must not be null");
		
		this.domain = domain;
		this.port = port;
		
		if (requestBodyFile != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(requestBodyFile).toFile()))) {
				requestBody = (JsonObject) builder.build(reader);
			}
		} else {
			requestBody = null;
		}
	}
	
	@Override
	public void execute() throws Exception {
		EmeraldTermuxApi api = EmeraldTermuxApiProvider.provideApi(domain, port);
		Call<JsonObject> call;
		
		if (requestBody == null) {
			call = api.getFullStatus();
		} else {
			call = api.getSelectedStatus(requestBody);
		}
		
		Response<JsonObject> response = call.execute();
		
		if (response.isSuccessful()) {
			System.out.println(response.body().toJson());
		} else {
			System.out.println(response.errorBody().string());
		}
	}
}
