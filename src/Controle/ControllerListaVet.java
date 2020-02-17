package Controle;

import Modelo.Veterinario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ControllerListaVet {

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
    private TextField cpfFunc;

    @FXML
    private TextField crmvFunc;

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
    private TableView<Veterinario> tabela;

    @FXML
    private TableColumn<Veterinario, String> nomeTabela;

    @FXML
    private TableColumn<Veterinario, String> cpfTabela;

    @FXML
    private TableColumn<Veterinario, String> crmvTabela;

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
        carregarTableViewVeterinario();
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemVet(newValue));
    }

    private List<Veterinario> listVet;
    private ObservableList<Veterinario> observableListVet;

    public void carregarTableViewVeterinario(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        crmvTabela.setCellValueFactory(new PropertyValueFactory<>("crmv"));

        listVet = new ArrayList<>(em.createQuery("SELECT v FROM Veterinario v", Veterinario.class).getResultList());

        observableListVet = FXCollections.observableArrayList((listVet));
        tabela.setItems(observableListVet);
        em.close();
    }

    public void selectItemVet(Veterinario vet){
        if (vet != null){
            nomeFunc.setText(vet.getNome());
            enderecoFunc.setText(vet.getEndereco());
            telefoneFunc.setText(vet.getTelefone());
            crmvFunc.setText(vet.getCrmv());
            cpfFunc.setText(vet.getCpf());
        }else{
            nomeFunc.setText("");
            enderecoFunc.setText("");
            telefoneFunc.setText("");
            crmvFunc.setText("");
            cpfFunc.setText("");
        }
    }
}
