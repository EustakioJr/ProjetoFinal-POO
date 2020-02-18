package Dao;

import Conection.ConnectionFactory;
import Modelo.Animal;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Boolean atualizar(Animal animal){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(animal);
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

    public Boolean deletar(Animal animal){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Animal a = em.find(Animal.class, animal.getId());

            em.getTransaction().begin();
            em.remove(a);
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

    public List<Animal> buscaFiltragem(String busca){
        EntityManager em = new ConnectionFactory().getConnection();
        return  em.createQuery(busca, Animal.class).getResultList();

    }


}