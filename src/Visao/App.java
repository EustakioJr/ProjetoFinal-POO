package Visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    private static Scene telaInicio;
    private static Scene telaHome;
    private static Scene telaCadastro;
    private static Scene listaFuncionario;
    private static Scene listaConsulta;
    private static Scene listaCliente;
    private static Scene listaAnimal;
    private static Scene cadastroVeterinario;
    private static Scene cadastroRecepcionista;
    private static Scene cadastroConsulta;
    private static Scene cadastroCliente;
    private static Scene cadastroAnimal;

    @Override
    public void start(Stage PrimaryStage) throws Exception {

        stage = PrimaryStage;
        PrimaryStage.setTitle("Projeto Final POO");

        Parent fxmlTelaInicio = FXMLLoader.load(getClass().getResource("telaInicio.fxml"));
        telaInicio = new Scene(fxmlTelaInicio, 600, 400);

        Parent fxmlTelaHome = FXMLLoader.load(getClass().getResource("telaHome.fxml"));
        telaHome = new Scene(fxmlTelaHome, 600, 400);

        Parent fxmlTelaCadastro = FXMLLoader.load(getClass().getResource("telaCadastro.fxml"));
        telaCadastro = new Scene(fxmlTelaCadastro, 600, 400);

        Parent fxmlListaFuncionario = FXMLLoader.load(getClass().getResource("ListaFuncionario.fxml"));
        listaFuncionario = new Scene(fxmlListaFuncionario, 600, 400);

        Parent fxmlListaConsulta = FXMLLoader.load(getClass().getResource("ListaConsulta.fxml"));
        listaConsulta = new Scene(fxmlListaConsulta, 600, 400);

        Parent fxmlListaCliente = FXMLLoader.load(getClass().getResource("ListaCliente.fxml"));
        listaCliente = new Scene(fxmlListaCliente, 600, 400);

        Parent fxmlListaAnimal = FXMLLoader.load(getClass().getResource("ListaAnimal.fxml"));
        listaAnimal = new Scene(fxmlListaAnimal, 600, 400);

        Parent fxmlCadastroVeterinario = FXMLLoader.load(getClass().getResource("cadastroVeterinario.fxml"));
        cadastroVeterinario = new Scene(fxmlCadastroVeterinario, 600, 400);

        Parent fxmlCadastroRecepcionista = FXMLLoader.load(getClass().getResource("cadastroRecepcionista.fxml"));
        cadastroRecepcionista = new Scene(fxmlCadastroRecepcionista, 600, 400);

        Parent fxmlCadastroConsulta = FXMLLoader.load(getClass().getResource("cadastroConsulta.fxml"));
        cadastroConsulta = new Scene(fxmlCadastroConsulta, 600, 400);

        Parent fxmlCadastroCliente = FXMLLoader.load(getClass().getResource("cadastroCliente.fxml"));
        cadastroCliente = new Scene(fxmlCadastroCliente, 600, 400);

        Parent fxmlCadastroAnimal = FXMLLoader.load(getClass().getResource("cadastroAnimal.fxml"));
        cadastroAnimal = new Scene(fxmlCadastroAnimal, 600, 400);

        //Seta a tela que sera mostrada ao iniciar o aplicativo
        PrimaryStage.setScene(telaInicio);
        //Inicia a tela no aplicativo
        PrimaryStage.show();
    }

    public static void trocaTela(String idTela){
        switch (idTela) {
            case "inicio":
                stage.setScene(telaInicio);
                break;
            case "home":
                stage.setScene(telaHome);
                break;
            case "cadastro":
                stage.setScene(telaCadastro);
                break;
            case "listaFunc":
                stage.setScene(listaFuncionario);
                break;
            case "listaConsulta":
                stage.setScene(listaConsulta);
                break;
            case "listaCliente":
                stage.setScene(listaCliente);
                break;
            case "listaAnimal":
                stage.setScene(listaAnimal);
                break;
            case "cadastroVet":
                stage.setScene(cadastroVeterinario);
                break;
            case "cadastroRecep":
                stage.setScene(cadastroRecepcionista);
                break;
            case "cadastroConsulta":
                stage.setScene(cadastroConsulta);
                break;
            case "cadastroCliente":
                stage.setScene(cadastroCliente);
                break;
            case "cadastroAnimal":
                stage.setScene(cadastroAnimal);
        }
    }

    public static void main(String[] args) {launch(args);}


}
