package Controle;

import Modelo.Recepcionista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ControllerListaFuncionario {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField cpfFunc;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField telefoneFunc;

    @FXML
    private TextField enderecoFunc;

    @FXML
    private TextField nomeFunc;

    @FXML
    private TableView<Recepcionista> tabela;

    @FXML
    private TableColumn<Recepcionista, String> nomeTabela;

    @FXML
    private TableColumn<Recepcionista, String> cpfTabela;

    @FXML
    void atualiza(ActionEvent event) {

    }

    @FXML
    void deleta(ActionEvent event) {

    }

    @FXML
    void filtrar(ActionEvent event) {

    }

    @FXML
    void irHome(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");
    }

    private List<Recepcionista> listRecep;
    private ObservableList<Recepcionista> observableListRecep;

    public void initialize(){
        carregarTableViewRecepcionista();
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemRecep(newValue));
    }

    public void carregarTableViewRecepcionista(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));


        listRecep = new ArrayList<>(em.createQuery("SELECT r FROM Recepcionista r", Recepcionista.class).getResultList());

        observableListRecep = FXCollections.observableArrayList((listRecep));
        tabela.setItems(observableListRecep);
        em.close();
    }

    public void selectItemRecep(Recepcionista Recep) {
        if (Recep != null) {
            nomeFunc.setText(Recep.getNome());
            enderecoFunc.setText(Recep.getEndereco());
            telefoneFunc.setText(Recep.getTelefone());
            cpfFunc.setText(Recep.getCpf());
        } else {
            nomeFunc.setText("");
            enderecoFunc.setText("");
            telefoneFunc.setText("");
            cpfFunc.setText("");
        }
    }
}
