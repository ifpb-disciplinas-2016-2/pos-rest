/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pos.resources.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class IdApater extends XmlAdapter<Integer, Integer>{

    @Override
    public Integer unmarshal(Integer v) throws Exception {
        
        return 0;
    }

    @Override
    public Integer marshal(Integer v) throws Exception {
        
        return v;
    }

    
}
