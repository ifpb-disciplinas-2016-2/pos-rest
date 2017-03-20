package ifpb.pos.entity;

import ifpb.pos.resources.ClientResources;
import ifpb.pos.resources.ProductResource;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/03/2017, 10:51:35
 */
//@XmlRootElement
public class OrderSimple implements Serializable {

    private int id;
    private Link client;
    private List<Link> products = new ArrayList<>();

    public OrderSimple() {

    }

    public OrderSimple(Order order, UriInfo info) {
        this.id = order.getId();
        buildLinkClient(order.getClient(), info);
        buildLinkProducts(order.getProducts(), info);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Link getClient() {
        return client;
    }

    public void setClient(Link client) {
        this.client = client;
    }

    public List<Link> getProducts() {
        return products;
    }

    public void setProducts(List<Link> products) {
        this.products = products;
    }

    private void buildLinkClient(Client client, UriInfo info) {
        String clienteId = String.valueOf(client.getId());
        URI uriCliente = info.getBaseUriBuilder() // "http://localhost:8088/pos-rest-venda/ws        
                .path(ClientResources.class) //.../ws/client
                .path(clienteId) //.../ws/client/201
                .build();

        this.client = new Link("client", uriCliente.toString());

    }

    private void buildLinkProducts(List<Product> products, UriInfo info) {

        products.forEach(produto -> {
            String produtoId = String.valueOf(produto.getId());
            URI uri = info.getBaseUriBuilder() //"http://localhost:8088/pos-rest-venda/ws
                    .path(ProductResource.class) //.../ws/product
                    .path(produtoId) //.../ws/product/201
                    .build();
            this.products.add(new Link("product", uri.toString()));
        });
    }

}
