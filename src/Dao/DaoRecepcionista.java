package Dao;

import Conection.ConnectionFactory;
import Modelo.Recepcionista;
import Modelo.Veterinario;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Boolean atualizar(Recepcionista recep){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(recep);
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

    public Boolean deletar(Recepcionista recep){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Recepcionista r = em.find(Recepcionista.class, recep.getCpf());

            em.getTransaction().begin();
            em.remove(r);
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

    public List<Recepcionista> buscaFiltragem(String busca){
        EntityManager em = new ConnectionFactory().getConnection();
        return  em.createQuery(busca, Recepcionista.class).getResultList();

    }
}