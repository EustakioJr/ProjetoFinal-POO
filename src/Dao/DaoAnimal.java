package Dao;

import Conection.ConnectionFactory;
import Modelo.Animal;

import javax.persistence.EntityManager;

public class DaoAnimal {

    public Boolean salvar (Animal animal){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(animal);
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