package Controle;

import Modelo.Login;

public class UsuarioLogado {

    private static UsuarioLogado uniqueInstance;
    private boolean ehAdm = false;

    public boolean isEhAdm() {
        return ehAdm;
    }

    public void setEhAdm(boolean ehAdm) {
        this.ehAdm = ehAdm;
    }

    private UsuarioLogado(){

    }

    public static synchronized UsuarioLogado getInstance(){
        if (uniqueInstance ==null)
            uniqueInstance = new UsuarioLogado();

        return uniqueInstance;
    }
}
