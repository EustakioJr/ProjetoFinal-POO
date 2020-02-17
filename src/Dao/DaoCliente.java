package Dao;

import Conection.ConnectionFactory;
import Modelo.Cliente;

import javax.persistence.EntityManager;

public class DaoCliente {

    public Boolean salvar (Cliente cliente){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            resultado=true;
        }catch (Exception e){
            em.getTransaction().rollback();
            resultado=false;
        }finally{
            em.close();
        }

        return resultado;
    }
}