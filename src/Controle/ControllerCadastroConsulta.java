package Controle;

import Modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Date;

public class ControllerCadastroConsulta {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoMotivo;

    @FXML
    private TextField campoAnimal;

    @FXML
    private TextField campoVeterinario;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private DatePicker campoData;

    @FXML
    void cadastrar(ActionEvent event) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();

        Integer idAnimal = Integer.parseInt(campoAnimal.getText());
        Consulta novo = new Consulta();
        novo.setData(Date.valueOf(campoData.getValue()));
        novo.setMotivo(campoMotivo.getText());
        novo.setAnimal(em.createQuery("SELECT a FROM Animal a WHERE a.id = :animal", Animal.class)
                .setParameter("animal", idAnimal)
                .getSingleResult());
        novo.setVeterinario(em.createQuery("SELECT v FROM Veterinario v WHERE v.crmv = :vet", Veterinario.class)
                .setParameter("vet", campoVeterinario.getText())
                .getSingleResult());
        novo.setFoiAtendido(false);

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
