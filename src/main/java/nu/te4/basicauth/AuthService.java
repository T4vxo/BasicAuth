/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.basicauth;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class AuthService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUser(@HeaderParam("Authorization") String authorization) {
        Credentials credentials = CredentialFacade.createCredentials(authorization);
        if (CredentialFacade.verify(credentials.getUsername(), credentials.getPassword())) {
            Gson gson = new Gson();
            return Response.ok(gson.toJson(credentials)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    public Response postUser(@HeaderParam("Authorization") String authorization) {
        Credentials credentials = CredentialFacade.createCredentials(authorization);
        CredentialFacade.save(credentials);
        return Response.status(Response.Status.CREATED).build();
    }
}
