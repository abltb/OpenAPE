package org.openape.web;

import java.io.IOException;

import org.openape.server.Main;
import org.openape.server.database.mongoDB.DatabaseConnection;
import org.openape.server.database.resources.ResourceList;
import org.openape.server.rest.SuperRestInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.servlet.SparkApplication;

public class Init implements SparkApplication {
	static Logger logger = LoggerFactory.getLogger(Init.class	);
    @Override
    public void init() {
        // Start rest api and database connection.
        DatabaseConnection.getInstance();
        try {
            ResourceList.getInstance();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new SuperRestInterface();
    }

}
