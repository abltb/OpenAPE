package org.openape.server.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openape.api.Messages;
import org.openape.server.Main;
import org.openape.server.requestHandler.EnvironmentContextRequestHandler;
import org.openape.server.requestHandler.EquipmentContextRequestHandler;
import org.openape.server.requestHandler.ListingRequestHandler;
import org.openape.server.requestHandler.ResourceDescriptionRequestHandler;
import org.openape.server.requestHandler.ResourceRequestHandler;
import org.openape.server.requestHandler.TaskContextRequestHandler;
import org.openape.server.requestHandler.UserContextRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Spark;

public class SuperRestInterface {
	static Logger logger = LoggerFactory.getLogger(SuperRestInterface.class	);
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_NO_CONTENT = 204;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    /**
     * Get a sent json object from a request.
     *
     * @param req
     *            spark request containing the object.
     * @param objectType
     *            expected class of the object.
     * @return received java object of the type objectType.
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    protected static <T> Object extractObjectFromRequest(Request req, Class<T> objectType)
            throws IOException, JsonParseException, JsonMappingException {
        final ObjectMapper mapper = new ObjectMapper();
        final Object recievedObject = mapper.readValue(req.body(), objectType);
        return recievedObject;
    }

    /**
     * Constructor for the rest interface super class. Creates all rest end
     * points of the application.
     */
    public SuperRestInterface() {
        /**
         * test request to test if the server runs. Invoke locally using:
         * http://localhost:4567/hello if started from main.
         */

    	logger.info("Setting up REST API");	
        Spark.get("api", (req,res) -> new API()                                                                    );
    	Spark.get(
                Messages.getString("UserContextRESTInterface.HelloWorldURL"), (req, res) -> Messages.getString("UserContextRESTInterface.HelloWorld")); //$NON-NLS-1$ //$NON-NLS-2$

        Spark.get(
                Messages.getString("SuperRestInterface.HelloWorldURL"), (req, res) -> Messages.getString("SuperRestInterface.HelloWorld")); //$NON-NLS-1$ //$NON-NLS-2$


        EnvironmentContextRESTInterface
                .setupEnvironmentContextRESTInterface(new EnvironmentContextRequestHandler());
        EquipmentContextRESTInterface
                .setupEquipmentContextRESTInterface(new EquipmentContextRequestHandler());
        ListingRESTInterface.setupListingRESTInterface(new ListingRequestHandler());
        ResourceDescriptionRESTInterface
                .setupResourceDescriptionRESTInterface(new ResourceDescriptionRequestHandler());
        ResourceManagerRESTInterface.setupResourceManagerRESTInterface();
        ResourceRESTInterface.setupResourceRESTInterface(new ResourceRequestHandler());
        TaskContextRESTInterface.setupTaskContextRESTInterface(new TaskContextRequestHandler());
        UserContextRESTInterface.setupUserContextRESTInterface(new UserContextRequestHandler());
        logger.info("REST API successfully set up");
    }

}