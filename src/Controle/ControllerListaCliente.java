package Controle;

import Modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ControllerListaCliente {

    Boolean resultado;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField cpfCliente;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField telefoneCliente;

    @FXML
    private TextField enderecoCliente;

    @FXML
    private TextField nomeCliente;

    @FXML
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, String> nomeTabela;

    @FXML
    private TableColumn<Cliente, String> cpfTabela;

    @FXML
    private Label labelAviso;

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){

        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){

        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
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
        UsuarioLogado.getInstance().setEhAdm(false);
    }

    public void initialize(){
        carregarTableViewCliente();
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemCliente(newValue));
    }

    private List<Cliente> listCliente;
    private ObservableList<Cliente> observableListCliente;

    public void carregarTableViewCliente(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listCliente = new ArrayList<>(em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList());

        observableListCliente = FXCollections.observableArrayList((listCliente));
        tabela.setItems(observableListCliente);
        em.close();
    }

    public void selectItemCliente(Cliente cliente){
        if (cliente != null){
            nomeCliente.setText(cliente.getNome());
            enderecoCliente.setText(cliente.getEndereco());
            telefoneCliente.setText(cliente.getTelefone());
            cpfCliente.setText(cliente.getCpf());
        }else{
            nomeCliente.setText("");
            enderecoCliente.setText("");
            telefoneCliente.setText("");
            cpfCliente.setText("");
        }
    }

}
