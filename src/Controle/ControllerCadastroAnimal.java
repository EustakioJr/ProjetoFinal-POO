package Controle;

import Modelo.Animal;
import Modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ControllerCadastroAnimal {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoRaca;

    @FXML
    private TextField campoEspecie;

    @FXML
    private TextField campoPeso;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField campoIdade;

    @FXML
    private TextField campoDono;

    @FXML
    void cadastrar(ActionEvent event) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();

        em.getTransaction().begin();
        Animal novo = new Animal();
        novo.setNome(campoNome.getText());
        novo.setEspecie(campoEspecie.getText());
        novo.setRaca(campoRaca.getText());
        novo.setPeso(campoPeso.getText());
        novo.setIdade(campoIdade.getText());
        novo.setDono(em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :dono", Cliente.class)
                .setParameter("dono", campoDono.getText())
                .getSingleResult());

        em.persist(novo);
        em.getTransaction().commit();

        em.close();
    }

    @FXML
    void irHome(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");

    }

}
