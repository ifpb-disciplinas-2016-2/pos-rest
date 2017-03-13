/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.pos.resources;

import ifpb.dac.pos.entity.Client;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Path("/client")
public class ClientResources {

    private static final List<Client> CLIENTS = new ArrayList<>();

    @GET
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {

        GenericEntity<List<Client>> entityResponse = new GenericEntity<List<Client>>(CLIENTS) {
        };

        return Response.ok().entity(entityResponse).build();
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addClient(Client client) throws URISyntaxException {

        CLIENTS.add(client);
        client.setId(CLIENTS.size() - 1);

        return Response.created(new URI("/pos-rest/ws/client/" + client.getId())).entity(client).build();
    }

    @GET
    @Path("/{id}")
    public Response getClient(@PathParam("id") int id) {

        Client client = null;

        try {

            client = CLIENTS.get(id);
        } catch (Exception ex) {
        }

        Response response;

        if (client == null) {

            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {

            response = Response.ok().entity(client).build();
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateClient(@PathParam("id") int id, Client newClient) throws URISyntaxException {

        Client client = null;

        try {

            client = CLIENTS.get(id);
        } catch (Exception ex) {
        }

        Response response;

        if (client == null) {

            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {

            CLIENTS.set(id, newClient);

            response = Response.ok().entity(newClient).location(new URI("/pos-rest/ws/client/" + id)).build();
        }

        return response;

    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") int id) {

        Client client = null;

        try {

            client = CLIENTS.get(id);
        } catch (Exception ex) {
        }

        Response response;

        if (client == null) {

            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            
            CLIENTS.remove(client);
            response = Response.ok().build();
        }

        return response;
    }

}
