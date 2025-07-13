package com.matthiasrothe.emerald.termux.cli;

import com.matthiasrothe.emerald.termux.cli.commands.PauseCommand;
import com.matthiasrothe.emerald.termux.cli.commands.ResumeCommand;
import com.matthiasrothe.emerald.termux.cli.commands.ShutdownCommand;
import com.matthiasrothe.emerald.termux.cli.commands.StatusCommand;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Emerald Termux CLI", version = "0.0.1-SNAPSHOT", mixinStandardHelpOptions = true)
public class CliLauncher implements Runnable {

	@Option(names = { "-c", "--command" }, description = "The command to run. May be either of"
			+ " [status, pause, resume, shutdown]. Defaults to 'status'.")
	private String command = "status";
	
	@Option(names = { "-f", "--file" }, description = "The path to the file containing the request"
			+ " body. This is only taken into account if the command is 'status'.")
	private String requestBodyFile;
	
	@Option(names = { "-d", "--domain" }, description = "The domain where Emerald Termux is running."
			+ " May be a valid domain name or an IP address. Defaults to 'localhost'.")
	private String domain = "localhost";
	
	@Option(names = { "-p", "--port" }, description = "The port on which Emerald Termux is running."
			+ " Defaults to 8080.")
	private int port = 8080;
	
	public void run() {
		try {
			switch (command) {
				case "status":
					new StatusCommand(requestBodyFile, domain, port).execute();
					break;
				case "pause":
					new PauseCommand(domain, port).execute();
					break;
				case "resume":
					new ResumeCommand(domain, port).execute();
					break;
				case "shutdown":
					new ShutdownCommand(domain, port).execute();
					break;
				default:
					System.out.println("Unknown command option [" + command + "].");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		int exitCode = new CommandLine(new CliLauncher()).execute(args);
		System.exit(exitCode);
	}
}
