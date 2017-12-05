/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author jonata
 */
public class DaoCliente {

    public void inserir(Cliente cliente)
            throws SQLException, Exception {

        String sql = "INSERT INTO Cliente (nome, login, senha) "
                + "VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNomeCliente());
            statement.setString(2, cliente.getLogin());
            statement.setString(3, cliente.getSenhaCliente());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    //Deleta um cliente na tabela "cliente" do banco de dados
    public void deletar(Long id)
            throws SQLException, Exception {
        //Monta a string de remoção de um cliente no BD,
        //utilizando os dados do clientes passados como parâmetro
        String sql = "DELETE FROM Cliente "
                + "WHERE id = ?; ";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement statement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = Conexao.getConnection();
            //Cria um statement para execução de instruções SQL
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            //Exibe no console o que será executado no banco de dados
            System.out.println("Executando COMANDO SQL: " + sql);
            //Executa o comando no banco de dados
            statement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public void alterar(Cliente cliente)
            throws SQLException, Exception {
        String sql = "UPDATE Cliente SET Nome = ?, login = ?, senha = ? "
                + "WHERE id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNomeCliente());
            statement.setString(2, cliente.getLogin());
            statement.setString(3, cliente.getSenhaCliente());
            statement.setLong(4, cliente.getCodigoCliente());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public Cliente obter(Long id)
            throws SQLException, Exception {
        String sql = "SELECT * FROM Cliente WHERE id = ?;";

        PreparedStatement statement = null;
        Connection connection = null;
        Cliente cliente = new Cliente();
        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        System.out.println(statement.toString());
        ResultSet result = null;
        result = statement.executeQuery();

        while (result.next()) {

            cliente.setNomeCliente(result.getString("nome"));
            cliente.setLogin(result.getString("login"));
            cliente.setSenhaCliente(result.getString("senha"));
            cliente.setCodigoCliente(result.getLong("id"));
        }

        return cliente;
    }

    public List<Cliente> executarConsulta(String sql) throws SQLException, Exception {
        List<Cliente> listaClientes = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                Cliente cliente = new Cliente();
                cliente.setNomeCliente(result.getString("nome"));
                cliente.setLogin(result.getString("login"));
                cliente.setSenhaCliente(result.getString("senha"));
                cliente.setCodigoCliente(result.getLong("id"));
                listaClientes.add(cliente);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaClientes;
    }

    public List<Cliente> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Cliente;";

        return executarConsulta(sql);
    }

    public Cliente retornarCliente(Long id) throws
            SQLException, Exception {
        String sql = "SELECT * FROM Cliente "
                + " WHERE id = ?";
        Cliente cliente = new Cliente();
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);

        statement.setLong(1, id);
        System.out.println(statement.toString());
        System.out.println("Executando CONSULTA SQL: " + sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            cliente.setNomeCliente(result.getString("nome"));
            cliente.setLogin(result.getString("login"));
            cliente.setSenhaCliente(result.getString("senha"));
            connection.close();
            return cliente;
        }

        return null;
    }
}
