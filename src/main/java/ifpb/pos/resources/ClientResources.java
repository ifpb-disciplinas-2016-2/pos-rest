/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pos.resources;

import ifpb.pos.entity.Client;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
//@Stateless
public class ClientResources {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {

        List<Client> resultList = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        GenericEntity<List<Client>> entityResponse = new GenericEntity<List<Client>>(resultList) {
        };
        return Response.ok().entity(entityResponse).build();
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addClient(Client client) throws URISyntaxException {

        em.persist(client);

        return Response
                .created(new URI("/pos-rest/ws/client/" + client.getId()))
                .entity(client)
                .build();

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getClient(@PathParam("id") int id) {

        Client client = em.find(Client.class, id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(client).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateClient(@PathParam("id") int id, Client newClient) throws URISyntaxException {

        newClient.setId(id);
        Client clienteRetorno = em.merge(newClient);
        if (clienteRetorno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .ok()
                .entity(clienteRetorno)
                .location(new URI("/pos-rest/ws/client/" + id))
                .build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") int id) {

        Client clienteRetorno = em.find(Client.class, id);
        if (clienteRetorno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(clienteRetorno);
        return Response
                .ok()
                .entity(clienteRetorno)
                //                .noContent()
                .build();
    }

}
