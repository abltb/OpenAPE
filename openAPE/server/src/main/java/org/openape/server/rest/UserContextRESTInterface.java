package org.openape.server.rest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openape.api.usercontext.UserContext;
import org.openape.server.UserContextRequestHandler;
import static spark.Spark.*;

public class UserContextRESTInterface {
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    public UserContextRESTInterface(final UserContextRequestHandler requestHandler) {

        // Change port to default port.
        port(8080);

        /**
         * test request to test if the server runs. Invoke locally using:
         * http://localhost:8080/hello
         */
        get("/hello", (req, res) -> "Hello World");

        /**
         * Request 7.2.2 create user-context.
         */
        post("/api/user-context", (req, res) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                UserContext creation = mapper.readValue(req.body(), UserContext.class);
                if (!creation.isValid()) {
                    res.status(HTTP_STATUS_BAD_REQUEST);
                    return "";
                }
                int userContextId = 0; // TODO set.
                res.status(HTTP_STATUS_OK);
                res.type("application/json");
                return userContextId;
            } catch (JsonParseException jpe) {
                res.status(HTTP_STATUS_BAD_REQUEST);
                return "";
            }
        });

        /**
         * Request 7.2.3 get user-context. Used to get a specific user context
         * identified by ID.
         */
        get("/api/user-context/:user-context-id", (req, res) -> {
            String userContextId = req.params(":user-context-id");
            res.type("application/json");
            return requestHandler.getUserContextById(userContextId);
        });

        /**
         * Request 7.2.4 update user-context.
         */
        put("/api/user-context/user-context-id", (req, res) -> {
            String userContextId = req.params(":user-context-id");
            Object useContext = req.params(":user-context");
            requestHandler.updateUserContextById(userContextId, useContext);
            return null;
        });

        /**
         * Request 7.2.5 delete user-context.
         */
        delete("/api/user-context/user-context-id", (req, res) -> {
            String userContextId = req.params(":user-context-id");
            requestHandler.deleteUserContextById(userContextId);
            return null;
        });

    }
}
