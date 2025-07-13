# Emerald Termux CLI
A Java application to remote control Emerald Termux.

## Prerequisites
1. Have Java 21 or higher installed on your local machine
2. Have a current Maven installed on your local machine
3. Make sure [Emerald Termux](https://github.com/emerald-iot-ai/emerald-termux) is running on your Android Smartphone

## How to build Emerald Termux CLI
After cloning this repository to your local machine open a shell (e.g. cmd on Windows) and go to the directory you cloned the repository into. Run `mvn clean package` to build the executable jar file of Emerald Termux CLI.

## How to start Emerald Termux CLI
As a CLI Emerald Termux CLI has different options that you can use to remote control Emerald Termux. Each set of options runs a single command and then exits the CLI app. The most important options are `-d / --domain` specifying the host where Emerald Termux is running and `-c / --command` specifying the command you issue at Emerald Termux. The command options are:

1. `status`: Get status information about the components of Emerald Termux
2. `pause`: Pause the components of Emerald Termux, most importantly pause sending sensor data to [Emerald DeepLearning4J Data Recorder](https://github.com/emerald-iot-ai/emerald-dl4j-recorder)
3. `resume`: Continue sending sensor data to Emerald DeepLearning4J Data Recorder
4. `shutdown`: Terminate Emerald Termux

A typical command to run Emerald Termux CLI from your local machine is therefore: `java -jar emerald-termux-cli-0.0.1-SNAPSHOT.jar -d <IP address of your Android Smartphone> -c status`.

A typical command to run Emerald Termux CLI from your Android Smartphone would be: `java -jar emerald-termux-cli-0.0.1-SNAPSHOT.jar -c shutdown` for example.

Make sure to always run the `shutdown` command to terminate Emerald Termux when your done recording your samples.

That's it!

**Happy recording! :-)**
