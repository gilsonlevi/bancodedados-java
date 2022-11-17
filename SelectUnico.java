package ConexaoComBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectUnico {
	public static void main(String[] args) throws SQLException {

		Connection conexao = DriverManager.getConnection("jdbc:mysql://10.225.0.4/20201164010004_agenda",
				"20201164010004", "20201164010004+alves");

		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o email da busca: ");
		String email = teclado.nextLine();

		teclado.close();

		PreparedStatement stmt = conexao.prepareStatement("select * from contato where email = ?");
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
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
