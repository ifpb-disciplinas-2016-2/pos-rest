package ifpb.pos.resources;

import ifpb.pos.entity.Product;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.Stateless;
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
 * @author natarajan && Victor Hugo
 */
@Path("product")
@Stateless
public class ProductResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Product> resultList = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        GenericEntity<List<Product>> entityResponse = new GenericEntity<List<Product>>(resultList) {
        };
        return Response.ok().entity(entityResponse).build();

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response showProduct(@PathParam("id") int id) {
        Product product = em.find(Product.class, id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(product).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduct(Product product) throws URISyntaxException {
        em.persist(product);
        return Response
                .created(new URI("/pos-rest/ws/product/" + product.getId()))
                .entity(product)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateProduct(@PathParam("id") int id, Product product) throws URISyntaxException {
        product.setId(id);
        Product produtoRetorno = em.merge(product);
        if (produtoRetorno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .ok()
                .entity(produtoRetorno)
                .location(new URI("/pos-rest/ws/product/" + id))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        Product produtoRetorno = em.find(Product.class, id);
        if (produtoRetorno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(produtoRetorno);
        return Response
                .ok()
                .entity(produtoRetorno)
//                .noContent()
                .build();
    }

}
