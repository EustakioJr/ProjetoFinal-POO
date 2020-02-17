package Controle;

import Dao.DaoAnimal;
import Modelo.Animal;
import javafx.beans.property.SimpleStringProperty;
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

public class ControllerListaAnimal {

    Boolean resultado;

    Animal animalSelecionado;

    DaoAnimal daoAnimal;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoEspecie;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField idadeAnimal;

    @FXML
    private TextField pesoAnimal;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField racaAnimal;

    @FXML
    private TextField especieAnimal;

    @FXML
    private TextField nomeAnimal;

    @FXML
    private TableView<Animal> tabela;

    @FXML
    private TableColumn<Animal, Integer> idTabela;

    @FXML
    private TableColumn<Animal, String> nomeTabela;

    @FXML
    private TableColumn<Animal, String> especieTabela;

    @FXML
    private TableColumn<Animal, String> cpfDonoTabela;

    @FXML
    private Label labelAviso;

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Animal a = new Animal();
            a.setId(animalSelecionado.getId());
            a.setNome((campoNome.getText().isEmpty())? campoNome.getText():campoNome.getText());
            a.setEspecie((campoEspecie.getText().isEmpty()) ? animalSelecionado.getEspecie() : campoEspecie.getText());
            a.setRaca((racaAnimal.getText().isEmpty())? animalSelecionado.getRaca():racaAnimal.getText());
            a.setIdade((idadeAnimal.getText().isEmpty())?animalSelecionado.getIdade(): idadeAnimal.getText());
            a.setDono(a.getDono());

            daoAnimal.atualizar(a);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            daoAnimal.deletar(animalSelecionado);
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

    private List<Animal> listAnimal;
    private ObservableList<Animal> observableListAnimal;

    public void initialize(){
        daoAnimal = new DaoAnimal();

        carregarTabelaAnimal();

        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemAnimal(newValue));
    }

    public void carregarTabelaAnimal(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();

        idTabela.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        especieTabela.setCellValueFactory(new PropertyValueFactory<>("especie"));
        cpfDonoTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getDono().getCpf()));

        listAnimal = new ArrayList<>(em.createQuery("SELECT a FROM Animal a",Animal.class).getResultList());

        observableListAnimal = FXCollections.observableArrayList(listAnimal);
        tabela.setItems(observableListAnimal);
        em.close();
    }

    public void selectItemAnimal(Animal animal){
        if (animal != null){
            animalSelecionado = animal;
            nomeAnimal.setText(animal.getNome());
            especieAnimal.setText(animal.getEspecie());
            racaAnimal.setText(animal.getRaca());
            pesoAnimal.setText(animal.getPeso());
            idadeAnimal.setText(animal.getIdade());
        }else{
            animalSelecionado = null;
            nomeAnimal.setText("");
            especieAnimal.setText("");
            racaAnimal.setText("");
            pesoAnimal.setText("");
            idadeAnimal.setText("");
        }
    }

}
