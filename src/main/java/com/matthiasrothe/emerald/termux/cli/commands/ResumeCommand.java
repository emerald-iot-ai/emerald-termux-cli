package com.matthiasrothe.emerald.termux.cli.commands;

import java.util.Objects;

import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApi;
import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApiProvider;

import net.sf.jetro.tree.JsonObject;
import retrofit2.Response;

public class ResumeCommand implements Command {
	private final String domain;
	private final int port;
	
	public ResumeCommand(final String domain, final int port) {
		Objects.requireNonNull(domain, "domain must not be null");
		
		this.domain = domain;
		this.port = port;
	}
	
	@Override
	public void execute() throws Exception {
		EmeraldTermuxApi api = EmeraldTermuxApiProvider.provideApi(domain, port);
		Response<JsonObject> response = api.resume().execute();
		System.out.println(response.body().toJson());
	}
}
