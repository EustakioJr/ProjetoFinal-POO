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
            a.setNome(animalSelecionado.getNome());
            a.setEspecie( animalSelecionado.getEspecie());
            a.setRaca(animalSelecionado.getRaca());
            a.setIdade(idadeAnimal.getText());
            a.setPeso(pesoAnimal.getText());
            a.setDono(animalSelecionado.getDono());
            daoAnimal.atualizar(a);
            limpaCampo();
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        carregarTabelaCompleta();
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            daoAnimal.deletar(animalSelecionado);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        carregarTabelaCompleta();
    }

    @FXML
    void filtrar(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a FROM Animal a");
        if (!campoNome.getText().isEmpty() || !campoCPF.getText().isEmpty() || !campoEspecie.getText().isEmpty()){
            sb.append(" WHERE");

            if (!campoNome.getText().isEmpty()) sb.append(" a.nome = '"+ campoNome.getText()+"'");
            if (!campoCPF.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty())? " AND": "")
                            +" a.dono.cpf = '"
                            + campoCPF.getText()
                            +"'"
            );
            if (!campoEspecie.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty()||!campoCPF.getText().isEmpty())? " AND" : "")
                            +" a.especie = '"
                            + campoEspecie.getText()
                            +"'"
            );
        }

        carregarTabelaAnimal(daoAnimal.buscaFiltragem(sb.toString()));
        limpaCampo();
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

    private List<Animal> listAnimal;
    private ObservableList<Animal> observableListAnimal;

    public void initialize(){
        daoAnimal = new DaoAnimal();

        carregarTabelaCompleta();

        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemAnimal(newValue));
    }

    void carregarTabelaCompleta() {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        carregarTabelaAnimal(new ArrayList<Animal>(em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList()));
        em.close();
    }

    public void carregarTabelaAnimal(List<Animal> listaAnimais){


        idTabela.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        especieTabela.setCellValueFactory(new PropertyValueFactory<>("especie"));
        cpfDonoTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getDono().getCpf()));

        listAnimal = listaAnimais;

        observableListAnimal = FXCollections.observableArrayList(listAnimal);
        tabela.setItems(observableListAnimal);
    }

    public void selectItemAnimal(Animal animal){
        if (animal != null){
            System.out.println(animal.toString());
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

    public void limpaCampo(){
        nomeAnimal.setText("");
        especieAnimal.setText("");
        racaAnimal.setText("");
        pesoAnimal.setText("");
        idadeAnimal.setText("");
    }

}
