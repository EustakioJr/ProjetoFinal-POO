package Controle;

import Dao.DaoConsulta;
import Excecao.DataInvalida;
import Modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ControllerCadastroConsulta {

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

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
    private Label labelAviso;

    @FXML
    private Button botaoVoltar;

    @FXML
    void voltar(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("cadastro");
    }

    @FXML
    void cadastrar(ActionEvent event) throws DataInvalida {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();

        Boolean resultado;
        Integer idAnimal = Integer.parseInt(campoAnimal.getText());
        Consulta novo = new Consulta();

        System.out.println(campoData.getValue().compareTo(LocalDate.now()));
        if (campoData.getValue().compareTo(LocalDate.now()) < 0){
            labelAviso.setText("Data Invalida");
            throw new DataInvalida("Data Inferior a atual.");
        }
        novo.setData(Date.valueOf(campoData.getValue()));
        novo.setMotivo(campoMotivo.getText());
        novo.setAnimal(em.createQuery("SELECT a FROM Animal a WHERE a.id = :animal", Animal.class)
                .setParameter("animal", idAnimal)
                .getSingleResult());
        novo.setVeterinario(em.createQuery("SELECT v FROM Veterinario v WHERE v.crmv = :vet", Veterinario.class)
                .setParameter("vet", campoVeterinario.getText())
                .getSingleResult());
        novo.setFoiAtendido(false);

        DaoConsulta dao = new DaoConsulta();
        resultado = dao.salvar(novo);

        if (resultado){
            labelAviso.setText("Cadastro realizado com sucesso");
        }else{
            labelAviso.setText("Falha ao realizar Cadastro");
        }


        em.close();
    }

    @FXML
    void irHome(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("inicio");
        UsuarioLogado.getInstance().setEhAdm(false);
    }

    public void limpaCampo(){

    }

}
