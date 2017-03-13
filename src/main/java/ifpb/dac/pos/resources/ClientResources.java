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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Path("/client")
public class ClientResources {

    private static final List<Client> clients = new ArrayList<>();

    @GET
    public Response getAll() {

        return Response.ok().entity(clients).build();
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addClient(Client client) throws URISyntaxException {

        clients.add(client);
        client.setId(clients.size() - 1);

        return Response.created(new URI("/client/" + client.getId())).entity(client).build();
    }

    @GET
    @Path("/{id}")
    public Response getClient(@PathParam("id") int id) {

        Client client = null;

        try {

            client = clients.get(id);
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

//    @PUT
//    @Path("/id")
//    public Response updateClient(@PathParam("id") int id, Client client){
//        
//        
//    }
}
