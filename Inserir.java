package ConexaoComBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Inserir {
	public static void main(String[] args) throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://10.225.0.4/20201164010004_agenda",
				"20201164010004", "20201164010004+alves");

		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite o nome: ");
		String nome = teclado.nextLine();

		System.out.println("Digite o email: ");
		String email = teclado.nextLine();

		System.out.println("Digite o idade: ");
		int idade = teclado.nextInt();

		PreparedStatement stmt = conexao.prepareStatement("insert into contato (nome, email, idade) values (?, ?, ?)");
		stmt.setString(1, nome);
		stmt.setString(2, email);
		stmt.setInt(3, idade);

		int linhas_modificadas = stmt.executeUpdate();
		System.out.println("Linhas modificadas: " + linhas_modificadas);

		conexao.close();
		stmt.close();
	}
}
