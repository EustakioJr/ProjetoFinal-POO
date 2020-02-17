package Dao;

import Conection.ConnectionFactory;
import Modelo.Consulta;

import javax.persistence.EntityManager;

public class DaoConsulta {

    public Boolean salvar (Consulta consulta){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(consulta);
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