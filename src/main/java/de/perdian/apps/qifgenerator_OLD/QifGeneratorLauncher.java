package de.perdian.apps.qifgenerator_OLD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.perdian.apps.qifgenerator_OLD.fx.QifGeneratorApplication;
import javafx.application.Application;

public class QifGeneratorLauncher {

    private static final Logger log = LoggerFactory.getLogger(QifGeneratorApplication.class);

    public static void main(String[] args) {

        log.info("Launching application");
        Application.launch(QifGeneratorApplication.class);

    }

}
