/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jsf.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Alexandre
 */
@ManagedBean
@RequestScoped
public class BeanCidades extends br.jsf.model.JsfMCidade {

    /**
     * Creates a new instance of BeanCidades
     */
    public BeanCidades() {
    }
        
    public java.util.Collection<br.data.entity.Cidade> getAll(){
        return new br.data.crud.CrudCidade().getAll();
    }
        
    public String persist(){
        new br.data.crud.CrudCidade().persist(this.createCid());
        return null;
    }

}
