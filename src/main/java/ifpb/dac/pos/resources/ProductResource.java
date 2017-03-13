/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.pos.resources;

import ifpb.dac.pos.entity.Product;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author natarajan
 */
@Path("products")
public class ProductResource {
   
    private static final List<Product> products = Arrays.asList(new Product(1L, "tv", "TV 32''"));
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll(){
            
        return products;
        
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product showProduct(@PathParam("id") int id) {
        return products.get(id);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduct(Product product) throws URISyntaxException {
        
        products.add(product);
        product.setID(new Long(products.size() + 1));
        
        return Response.created(new URI("/product" + product.getID())).entity(product).build();    
    }
    
    
}
