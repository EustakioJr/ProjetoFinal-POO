package Controle;

import Dao.DaoAnimal;
import Modelo.Animal;
import Modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class ControllerCadastroAnimal {

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

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
    private Label labelAviso;

    @FXML
    private Button botaoVoltar;

    @FXML
    void voltar(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("cadastro");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();

        em.getTransaction().begin();
        Boolean resultado;
        Animal novo = new Animal();
        novo.setNome(campoNome.getText());
        novo.setEspecie(campoEspecie.getText());
        novo.setRaca(campoRaca.getText());
        novo.setPeso(campoPeso.getText());
        novo.setIdade(campoIdade.getText());
        novo.setDono(em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :dono", Cliente.class)
                .setParameter("dono", campoDono.getText())
                .getSingleResult());

        DaoAnimal dao = new DaoAnimal();
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
        campoDono.setText("");
        campoEspecie.setText("");
        campoIdade.setText("");
        campoNome.setText("");
        campoPeso.setText("");
        campoRaca.setText("");
    }

}
