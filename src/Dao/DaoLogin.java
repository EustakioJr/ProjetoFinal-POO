package Dao;

import Conection.ConnectionFactory;
import Controle.UsuarioLogado;
import Modelo.Login;

import javax.persistence.EntityManager;

public class DaoLogin {

    public String buscaLogin(String usuario, String senha){
        EntityManager em = new ConnectionFactory().getConnection();
        String resultado;
        try{
            Login login = em.createQuery("SELECT l FROM Login l WHERE l.usuario =:usuario AND l.senha =:senha",Login.class)
                    .setParameter("usuario", usuario)
                    .setParameter("senha", senha)
                    .getSingleResult();
            resultado = login.getTipo();
        }catch(Exception e){
            resultado = "null";
        }
        return resultado;
    }

    public Boolean salvar(Login login){
        boolean resultado;
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(login);
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
