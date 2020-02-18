package Dao;

import Conection.ConnectionFactory;
import Modelo.Cliente;
import Modelo.Consulta;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Boolean deletar(Consulta consulta){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Consulta c = em.find(Consulta.class, consulta.getId());

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

    public Boolean atualizar(Consulta consulta){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            Consulta c = em.find(Consulta.class, consulta.getId());

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

    public List<Consulta> buscaFiltragem(String busca){
        EntityManager em = new ConnectionFactory().getConnection();
        return  em.createQuery(busca, Consulta.class).getResultList();
    }
}