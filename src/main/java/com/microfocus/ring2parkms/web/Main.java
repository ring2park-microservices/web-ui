package com.microfocus.ring2parkms.web;

import com.microfocus.ring2parkms.web.services.WebServer;
import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Allow the server to be invoked from the command-line. The jar is built with
 * this as the <code>Main-Class</code> in the jar's <code>MANIFEST.MF</code>.
 *
 * @author Kevin A. Lee
 */
public class Main {

    final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ArrayList<String> bootOptions = new ArrayList<String>();

        Options options = new Options();

        Option sp = new Option("sp", "serverPort", true, "HTTP server port");
        sp.setRequired(false);
        options.addOption(sp);

        Option eh = new Option("eh", "eurekaHostname", true, "eureka instance hostname");
        eh.setRequired(false);
        options.addOption(eh);

        Option ep = new Option("ep", "eurekaPort", true, "eureka instance port");
        ep.setRequired(false);
        options.addOption(ep);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        String serverPort = "";
        String eurekaHost = "";
        String eurekaPort = "";

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("locations-server", options);
            System.exit(1);
            return;
        }

        if (cmd.hasOption("sp")) {
            serverPort = cmd.getOptionValue("sp").replace("'", "");
            if (!StringUtils.isEmpty(serverPort)) {
                logger.info("Overriding HTTP server port to: " + serverPort);
                System.setProperty("server.port", serverPort);
            }
        }
        bootOptions.add(serverPort);

        if (cmd.hasOption("eh")) {
            eurekaHost = cmd.getOptionValue("eh").replace("'", "");
            if (!StringUtils.isEmpty(eurekaHost)) {
                logger.info("Overriding eureka.instance.hostname to: " + eurekaHost);
                System.setProperty("eureka.instance.hostname", eurekaHost);
            }
        }
        bootOptions.add(eurekaHost);

        if (cmd.hasOption("ep")) {
            eurekaPort = cmd.getOptionValue("ep").replace("'", "");
            if (!StringUtils.isEmpty(eurekaPort)) {
                logger.info("Overriding eureka.instance.port to: " + eurekaPort);
                System.setProperty("eureka.instance.port", eurekaPort);
            }
        }
        bootOptions.add(eurekaPort);

        String[] bootArgs = new String[bootOptions.size()];
        bootArgs = bootOptions.toArray(bootArgs);
        WebServer.main(bootOptions.toArray(bootArgs));
    }

}


