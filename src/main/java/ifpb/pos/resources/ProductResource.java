/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pos.resources;

import ifpb.pos.entity.Product;
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
 * @author natarajan && Victor Hugo
 */
@Path("product")
public class ProductResource {

    private static List<Product> PRODUCTS = new ArrayList<Product>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        GenericEntity<List<Product>> entityResponse = new GenericEntity<List<Product>>(PRODUCTS) {
        };

        return Response.ok().entity(entityResponse).build();

    }

    @GET
    @Path("/{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response showProduct(@PathParam("id") int id) {
        Product product = null;
        
        try {
            product = PRODUCTS.get(id - 1);
        } catch(Exception e){
            
        }
        
        Response response;
        
        if (product == null) {
            
            response = Response.status(Response.Status.NOT_FOUND).build();
            
        } else {
            
            response = Response.ok().entity(product).build();
        }
        
        return response;
         
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduct(Product product) throws URISyntaxException {

        PRODUCTS.add(product);
        product.setId(PRODUCTS.size());

        return Response.created(new URI("/pos-rest/ws/product/" + product.getId())).entity(product).build();
    }
    
    @POST
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateProduct(@PathParam("id") int id, Product product) throws URISyntaxException {

        Product myProduct = null;

        try {
            
            myProduct = PRODUCTS.get(id - 1);
            
        } catch (Exception ex) {
        }

        Response response;

        if (myProduct == null) {

            response = Response.status(Response.Status.NOT_FOUND).build();
        
        } else {

            myProduct.setName(product.getName());
            myProduct.setDescription(product.getDescription());
            
            PRODUCTS.set(myProduct.getId() - 1, myProduct);

            response = Response.ok().entity(myProduct).location(new URI("/pos-rest/ws/client/" + id)).build();
        }

        return response;

    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {

        Product product = null;

        try {

            product = PRODUCTS.get(id - 1);
            
        } catch (Exception ex) {
        }

        Response response;

        if (product == null) {

            response = Response.status(Response.Status.NOT_FOUND).build();
            
        } else {
            
            PRODUCTS.remove(product);
            response = Response.ok().build();
            
        }

        return response;
    }

}
