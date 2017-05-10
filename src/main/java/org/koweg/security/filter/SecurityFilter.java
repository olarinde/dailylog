/**
 * 
 */
package org.koweg.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.koweg.demo.dailylog.api.ErrorMessage;

/**
 * @author larinde
 *
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final Object AUTHORISATION_HEADER = "Authorization";
    private static final String AUTHORISATION_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORISATION_HEADER);
        if (!authHeader.isEmpty()) {
            String authString = authHeader.get(0).replaceFirst(AUTHORISATION_PREFIX, "");
            String authToken = Base64.decodeAsString(authString);
            if (isValidUser(authToken)) {
                return;
            }
        }
        Response response = Response.status(Status.UNAUTHORIZED).entity(new ErrorMessage(Status.UNAUTHORIZED.name(), "Invalid or missing credentials")).build();
        requestContext.abortWith(response);
    }

    private boolean isValidUser(String authToken) {
        StringTokenizer tokenizer = new StringTokenizer(authToken, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();
        if (username.equals("testuser") && password.equals("testpassword")) {
            return true;
        }
        return false;
    }

}
