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
import modelo.Telefone;

/**
 *
 * @author jonat
 */
public class DaoTelefone {

    public static void inserir(Telefone telefone)
            throws SQLException, Exception {

        String sql = "INSERT INTO Telefone(numero) "
                + "VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, telefone.getNumeroTelefone());
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
    public static void deletar(Long id)
            throws SQLException, Exception {
        //Monta a string de remoção de um cliente no BD,
        //utilizando os dados do clientes passados como parâmetro
        String sql = "DELETE FROM Telefone "
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

    public static void alterar(Telefone telefone, Long id)
            throws SQLException, Exception {
        String sql = "UPDATE Telefone SET numero = ? "
                + "WHERE id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, telefone.getNumeroTelefone());
            statement.setLong(2, id);
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

    public static Telefone obter(Long id)
            throws SQLException, Exception {
        String sql = "SELECT * FROM Telefone WHERE id = ?;";

        PreparedStatement statement = null;
        Connection connection = null;
        Telefone telefone = new Telefone();
        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        System.out.println(statement.toString());
        ResultSet result = null;
        result = statement.executeQuery();

        while (result.next()) {

            telefone.setNumeroTelefone(result.getString("numero"));
            telefone.setCodigoTelefone(result.getLong("id"));
        }

        return telefone;
    }

    public static List<Telefone> executarConsulta(String sql) throws SQLException, Exception {
        List<Telefone> lista = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (lista == null) {
                    lista = new ArrayList<Telefone>();
                }
                Telefone telefone = new Telefone();
                telefone.setNumeroTelefone(result.getString("numero"));
                telefone.setCodigoTelefone(result.getLong("id"));
                lista.add(telefone);
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
        return lista;
    }

    public static List<Telefone> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Telefone;";

        return executarConsulta(sql);
    }

    public static Telefone retornarTelefone(Long id) throws
            SQLException, Exception {
        String sql = "SELECT * FROM Telefone "
                + " WHERE id = ?";
        Telefone telefone = new Telefone();
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);

        statement.setLong(1, id);
        System.out.println(statement.toString());
        System.out.println("Executando CONSULTA SQL: " + sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            telefone.setNumeroTelefone(result.getString("numero"));
            telefone.setCodigoTelefone(result.getLong("id"));
            connection.close();
            return telefone;
        }

        return null;
    }
}
