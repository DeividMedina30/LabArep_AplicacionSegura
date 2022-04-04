package edu.escuelaing.arep.segurdiadLogin.entities;

public class User {
    private String password;
    private String usuario;

    public User(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
