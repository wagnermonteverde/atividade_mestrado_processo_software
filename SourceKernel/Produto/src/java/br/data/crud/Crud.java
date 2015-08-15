/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.data.crud;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alexandre
 */
public abstract class Crud<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProdutoPU");

    public void persist(T object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void remove(int codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            
           java.lang.reflect.ParameterizedType ptipo;
            ptipo = (java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass();
            java.lang.reflect.Type tipo = ptipo.getActualTypeArguments()[0];
            Class classe = (Class)tipo;        
            T obj = (T)em.find(classe, codigo);
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();    
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
     
    
    public T getCodigo(int codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            
            java.lang.reflect.ParameterizedType ptipo;
            ptipo = (java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass();
            java.lang.reflect.Type tipo = ptipo.getActualTypeArguments()[0];
            Class classe = (Class)tipo;
             
            T obj = (T)em.find(classe, codigo);
            return obj;
            
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public java.util.Collection<T> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            
            java.lang.reflect.ParameterizedType ptipo;
            ptipo = (java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass();
            java.lang.reflect.Type tipo = ptipo.getActualTypeArguments()[0];
            Class classe = (Class)tipo;
            String consulta = classe.getSimpleName() +".findAll";
            System.out.println("###################  consulta ######################");
            System.out.println(consulta);
            return em.createNamedQuery(consulta).getResultList();
            
        } catch (Exception e) {
            System.out.println("########################################## erro");
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
 
    
    public void merge(T object) {
        EntityManager em = emf.createEntityManager();
        try {
            
//           java.lang.reflect.ParameterizedType ptipo;
//            ptipo = (java.lang.reflect.ParameterizedType)this.getClass().getGenericSuperclass();
//            java.lang.reflect.Type tipo = ptipo.getActualTypeArguments()[0];
//            Class classe = (Class)tipo;        
//            T obj = (T)em.find(classe, codigo);
            
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();    
        } catch (Exception e) {
            System.out.println("ERRO:");
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }
    
}
