package models;

public class Jogador {

	private String nome;
	private String email;
	private String senha;
	private int num_jogadas;
	private int pontos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getNum_jogadas() {
		return num_jogadas;
	}
	public void setNum_jogadas(int num_jogadas) {
		this.num_jogadas = num_jogadas;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	
}
