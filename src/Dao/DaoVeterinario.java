package Dao;

import Conection.ConnectionFactory;
import Modelo.Veterinario;

import javax.persistence.EntityManager;

public class DaoVeterinario {

    public Boolean salvar (Veterinario vet){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(vet);
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
