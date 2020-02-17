package Dao;

import Conection.ConnectionFactory;
import Modelo.Recepcionista;

import javax.persistence.EntityManager;

public class DaoRecepcionista {

    public Boolean salvar (Recepcionista recep){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(recep);
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