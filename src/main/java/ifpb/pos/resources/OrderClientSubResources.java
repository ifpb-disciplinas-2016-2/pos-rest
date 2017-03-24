package ifpb.pos.resources;

import ifpb.pos.entity.Client;
import ifpb.pos.entity.Order;
import ifpb.pos.entity.OrderSimple;
import ifpb.pos.entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 15/03/2017, 11:20:45
 */
@Path("/{idOrder}/client/{idClient}")
@Stateless
public class OrderClientSubResources {

    @PersistenceContext
    private EntityManager em;

    public OrderClientSubResources() {
    }

    public OrderClientSubResources(EntityManager em) {
        this.em = em;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrder(
            @PathParam("idOrder") int id, 
            @Context UriInfo info) {

        Order order = em.find(Order.class, id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        OrderSimple orderSimple = new OrderSimple(order, info);

        return Response.ok().entity(orderSimple).build();
    }
    
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response addClientInOrder(
            @PathParam("idOrder") int idOrder,
            @PathParam("idClient") int idClient, 
            @Context UriInfo info) {

        Order order = em.find(Order.class, idOrder);
        Client client = em.find(Client.class, idClient);
        order.setClient(client);
        Order orderSaved = em.merge(order);
        
        OrderSimple orderSimple = new OrderSimple(orderSaved, info);

        return Response
                .ok()
                .entity(orderSimple)
                .build();
    }
    
//    @PUT
////    @Path("{idOrder}/product/{idProduct}")
//    @Produces(value = MediaType.APPLICATION_XML)
////        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addProductInOrder(
//            @PathParam("idOrder") int idOrder,
//            @PathParam("idProduct") int idProduct,
//            @Context UriInfo info) {
//
//        Order order = em.find(Order.class, idOrder);
//        Product product = em.find(Product.class, idProduct);
//        order.addProduct(product);
//        Order orderSaved = em.merge(order);
//        
//        OrderSimple orderSimple = new OrderSimple(orderSaved, info);
//
//        return Response
//                .ok()
//                .entity(orderSimple)
//                .build();
//    }
}
