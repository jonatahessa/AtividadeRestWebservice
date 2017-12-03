package modelo;

import java.util.Date;
import java.util.List;

public class Cliente {

    private Long codigoCliente;

    private String nomeCliente;

    private String login;

    private String senhaCliente;

    public Cliente() {
    }

    public Cliente(Long codigoCliente, String nomeCliente, String login, String senhaCliente) {
        this.codigoCliente = codigoCliente;
        this.nomeCliente = nomeCliente;
        this.login = login;
        this.senhaCliente = senhaCliente;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }
}
