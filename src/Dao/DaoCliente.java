package Dao;

import Conection.ConnectionFactory;
import Modelo.Animal;
import Modelo.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoCliente {

    public Boolean salvar (Cliente cliente) {
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            resultado = false;
        } finally {
            em.close();
        }

        return resultado;
    }

    public Boolean atualizar(Cliente cliente){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Cliente c = em.find(Cliente.class, cliente.getCpf());

            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            resultado = false;
        } finally {
            em.close();
        }

        return resultado;
    }

    public Boolean deletar(Cliente cliente){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Cliente c = em.find(Cliente.class, cliente.getCpf());

            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            resultado = false;
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Cliente> buscaFiltragem(String busca){
        EntityManager em = new ConnectionFactory().getConnection();
        return  em.createQuery(busca, Cliente.class).getResultList();
    }
}