package org.example;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "ccload", description = "Load test an application", mixinStandardHelpOptions = true, version = "1.0", subcommands = {})
public class CCLoad  implements Callable<Integer> {
    @CommandLine.Option(names = {"-u", "--url"}, required = true, description = "URL of the application to load test")
    private String url;
    @CommandLine.Option(names = {"-n", "--number-of-requests"}, required = true, description = "Number of requests to send")
    private int numberOfRequests;
    @CommandLine.Option(names = {"-c", "--number-of-concurrent-requests"}, required = false, description = "Number of concurrent requests to send")
    private int numberOfConcurrentRequests;
    @CommandLine.Option(names = {"-f", "--file-name"}, required = false, description = "Name of the file containing urls to load test")
    private String fileName;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CCLoad()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception{
        var httpResponse =LoadTest.test(url, 10);
        System.out.println("Loading test application...");
        System.out.println("URL: " + url);
        System.out.println("Number of requests: " + numberOfRequests);
        System.out.println("Number of concurrent requests: " + numberOfConcurrentRequests);
        System.out.println("File name: " + fileName);
        System.out.println("Loading test application complete.");
        System.out.println("Results will be displayed shortly.");
        System.out.println("Response code: " + httpResponse);
        System.out.println("Thank you for using org.example.CCLoad.");
        System.out.println("Goodbye.");
        System.exit(0); // Exit the program after displaying the results.
        return 0; // Return 0 to indicate success. This is not necessary, but it is a good practice.
    }
}
