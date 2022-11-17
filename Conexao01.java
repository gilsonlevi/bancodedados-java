package ConexaoComBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao01 {
	public static void main(String[] args) throws SQLException {

		Connection conexao = DriverManager.getConnection("jdbc:mysql://10.225.0.4/20201164010004_agenda",
				"20201164010004", "20201164010004+alves");

		PreparedStatement stmt = conexao.prepareStatement("select * from contato");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			int idade = rs.getInt("idade");

			System.out.println("ID: " + id);
			System.out.println("Nome: " + nome);
			System.out.println("E-mail: " + email);
			System.out.println("IDade: " + idade);
			System.out.println("----------------------");
		}

		stmt.close();
		rs.close();
		conexao.close();

	}
}
