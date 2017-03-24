package ifpb.pos.resources;

import ifpb.pos.entity.Order;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 15/03/2017, 10:40:41
 */
@Path("/order")
@Stateless
public class OrderResources {

    @Context
    private ResourceContext resourceContext;

    @PersistenceContext
    private EntityManager em;

    //criar a venda?
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response newOrder(@Context UriInfo uriInfo) throws URISyntaxException {
        Order order = new Order();
        em.persist(order);

        String id = String.valueOf(order.getId());
        URI uriOrder = uriInfo.getBaseUriBuilder() // .../ws/
                .path(OrderResources.class) // .../ws/order
                .path(id) // .../ws/order/1
                .build();
//        URI uri = new URI("/pos-rest/ws/order/" + order.getId());

        return Response
                .created(uriOrder)
                .entity(order)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<Order> resultList = em.createQuery("SELECT c FROM Order c", Order.class).getResultList();
        GenericEntity<List<Order>> entityResponse = new GenericEntity<List<Order>>(resultList) {
        };
        return Response.ok().entity(entityResponse).build();
    }

    /*
    @PUT
    @Path("{idOrder}/client/{idClient}")
    public Response addClient() {

        Order order = em.find(Order.class, idOrder);
        Client client = em.find(Client.class, idClient);
        order.setClient(client);
        em.merge(order);
        
        return Response
                .ok()
                .entity(order)
                .build(); 
    } */
    @Path("{idOrder}/client/{idClient}")
    public OrderClientSubResources addClient() {
        //http://www.oracle.com/technetwork/pt/articles/java/java-ee7-y-jax-rs-2-2109106-ptb.html
        return this.resourceContext
                .getResource(OrderClientSubResources.class);
    }

    /*
    @PUT
    @Path("{idOrder}/product/{idProduct}")
    public Response addProduct(
            @PathParam("idOrder") int idOrder,
            @PathParam("idProduct") int idProduct) {

        Order order = em.find(Order.class, idOrder);
        Product product = em.find(Product.class, idProduct);
        order.addProduct(product);
        em.merge(order);

        return Response
                .ok()
                .entity(order)
                .build();
    } */
    @Path("{idOrder}/product/{idProduct}")
    public OrderProductSubResources addProduct() {
        return this.resourceContext
                .getResource(OrderProductSubResources.class);

    }

    /*@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOrder(@PathParam("id") int id, @Context UriInfo info) {

        Order order = em.find(Order.class, id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        OrderSimple orderSimple = new OrderSimple(order, info);

        return Response.ok().entity(orderSimple).build();
    } */
    @Path("{idOrder}")
    public OrderClientSubResources get() {
        return this.resourceContext
                .getResource(OrderClientSubResources.class);

    }

}
