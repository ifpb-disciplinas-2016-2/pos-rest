package ifpb.pos.resources.security;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/03/2017, 23:06:21
 */
@Provider
public class SecurityOrderFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        if (pathSecured(requestContext)) {

            List<String> headers = requestContext
                    .getHeaders().get("Authorization");

            if (heardValid(headers)) {
//                "Basic am9iOjEyMw=="    
                String authToken = headers.get(0);
                authToken = authToken.replaceFirst("Basic ", "");
                String decodedString = new String(
                        Base64.getDecoder()
                                .decode(authToken));

                StringTokenizer tokenizer = new StringTokenizer(
                        decodedString, ":");
                String user = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                //Basic am9iOjEyMw==
                if ("job".equals(user) && "123".equals(password)) {
                    return; // Response Ok
                }

            }
            Response unAuthorized = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Acesso não permitido")
                    .build();
            requestContext.abortWith(unAuthorized);
        }
    }

    private static boolean heardValid(List<String> headers) {
        return headers != null && headers.size() > 0;
    }

    private static boolean pathSecured(
            ContainerRequestContext requestContext) {
        return requestContext
                .getUriInfo()
                .getPath()
                .contains("order");
    }

}
