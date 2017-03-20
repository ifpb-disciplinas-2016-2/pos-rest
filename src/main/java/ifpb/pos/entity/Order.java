package ifpb.pos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 15/03/2017, 10:36:13
 */
@XmlRootElement
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Client client;
    
    @ManyToMany
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(Client client) {
        this();
        this.client = client;
    }

    public Order(int id, Client client) {
        this();
        this.id = id;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", client=" + client + ", products=" + products + '}';
    }
    

}
