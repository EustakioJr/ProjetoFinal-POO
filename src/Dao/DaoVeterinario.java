package Dao;

import Conection.ConnectionFactory;
import Modelo.Veterinario;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Boolean atualizar(Veterinario vet){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(vet);
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

    public Boolean deletar(Veterinario vet){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Veterinario v = em.find(Veterinario.class, vet.getCpf());

            em.getTransaction().begin();
            em.remove(v);
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

    public List<Veterinario> buscaFiltragem(String busca){
        EntityManager em = new ConnectionFactory().getConnection();
        return  em.createQuery(busca, Veterinario.class).getResultList();

    }
}
