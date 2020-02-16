package Controle;

import Modelo.Animal;
import Modelo.Consulta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ControllerListaAnimal {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

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

    private List<Animal> listAnimal;
    private ObservableList<Animal> observableListAnimal;

    public void initialize(){
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
           nomeAnimal.setText(animal.getNome());
           especieAnimal.setText(animal.getEspecie());
           racaAnimal.setText(animal.getRaca());
           pesoAnimal.setText(animal.getPeso());
           idadeAnimal.setText(animal.getIdade());
        }else{
            nomeAnimal.setText("");
            especieAnimal.setText("");
            racaAnimal.setText("");
            pesoAnimal.setText("");
            idadeAnimal.setText("");
        }
    }

}
