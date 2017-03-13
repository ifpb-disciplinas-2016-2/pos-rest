/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.pos.resources;

import ifpb.dac.pos.entity.Product;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("/products")
public class ProductResource {

    private static final List<Product> PRODUCTS = Arrays.asList(new Product(1, "tv", "TV 32''"));

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        GenericEntity<List<Product>> entityResponse = new GenericEntity<List<Product>>(PRODUCTS) {
        };

        return Response.ok().entity(entityResponse).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product showProduct(@PathParam("id") int id) {
        return PRODUCTS.get(id);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduct(Product product) throws URISyntaxException {

        PRODUCTS.add(product);
        product.setId(PRODUCTS.size() + 1);

        return Response.created(new URI("/product" + product.getId())).entity(product).build();
    }

}
