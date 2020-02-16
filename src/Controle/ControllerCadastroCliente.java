package Controle;

import Modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControllerCadastroCliente {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoEndereco;

    @FXML
    private Button botaoCadastrar;

    @FXML
    void cadastrar(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoPoo");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cliente novo = new Cliente();
        novo.setCpf(campoCPF.getText());
        novo.setEndereco(campoEndereco.getText());
        novo.setNome(campoNome.getText());
        novo.setTelefone(campoTelefone.getText());

        em.persist(novo);
        em.getTransaction().commit();

        em.close();
        emf.close();
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
