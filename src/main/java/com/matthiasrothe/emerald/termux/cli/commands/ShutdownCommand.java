package com.matthiasrothe.emerald.termux.cli.commands;

import java.io.IOException;
import java.util.Objects;

import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApi;
import com.matthiasrothe.emerald.termux.cli.retrofit.EmeraldTermuxApiProvider;

import net.sf.jetro.tree.JsonObject;
import retrofit2.Response;

public class ShutdownCommand implements Command {
	private final String domain;
	private final int port;
	
	public ShutdownCommand(final String domain, final int port) {
		Objects.requireNonNull(domain, "domain must not be null");
		
		this.domain = domain;
		this.port = port;
	}
	
	@Override
	public void execute() throws Exception {
		EmeraldTermuxApi api = EmeraldTermuxApiProvider.provideApi(domain, port);
		Response<JsonObject> response = api.shutdown().execute();
		System.out.println(response.body().toJson());
		System.out.println("Waiting for the applicaton to shut down...");
		
		try {
			Thread.sleep(10000);
			api.shutdown().execute();
		} catch (InterruptedException e) {
			throw e;
		} catch (IOException e) {
			System.out.println("Shutdown complete.");
		}
	}
}
