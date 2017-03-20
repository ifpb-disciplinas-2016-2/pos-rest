package ifpb.pos.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/03/2017, 11:08:26
 */
public class Link implements Serializable{

    private String rel;
    private String href;

    public Link() {
    }

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link{" + "rel=" + rel + ", href=" + href + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.rel);
        hash = 71 * hash + Objects.hashCode(this.href);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        if (!Objects.equals(this.rel, other.rel)) {
            return false;
        }
        if (!Objects.equals(this.href, other.href)) {
            return false;
        }
        return true;
    }
    
    
    

}
