
package exerciciodefixacao.controller;

import exerciciodefixacao.model.Pessoa;
import java.util.ArrayList;

// Classe responsável por gerenciar a lógica de negócios relacionada a pessoas
public class PessoaController {

    // Lista que armazena os objetos Pessoa em memória
    private ArrayList<Pessoa> pessoas;

    // Objeto responsável por carregar e salvar pessoas no arquivo
    private PessoaRepository pessoaRepository;

    // Construtor que inicializa o repositório e carrega as pessoas salvas
    public PessoaController() {

        this.pessoaRepository = new PessoaRepository();

        this.pessoas = pessoaRepository.carregarPessoas();
    }

    // Retorna uma nova lista contendo as pessoas cadastradas
    public ArrayList<Pessoa> listarPessoas() {
        return new ArrayList<>(pessoas);
    }

    // Adiciona uma nova pessoa à lista e salva no arquivo
    public void adicionarPessoa(int id, String nome, int idade, double altura) {

        Pessoa pessoa = new Pessoa(id, nome, idade, altura);

        pessoas.add(pessoa);

        pessoaRepository.salvarPessoas(pessoas);
    }

    // Altera uma pessoa existente com base no ID
    public boolean alterarPessoa(int id, String novoNome, int novaIdade, double novaAltura) {

        Pessoa pessoa = buscarPessoaPorId(id);

        if (pessoa != null) {

            pessoa.setNome(novoNome);
            pessoa.setIdade(novaIdade);
            pessoa.setAltura(novaAltura);

            pessoaRepository.salvarPessoas(pessoas);

            return true;
        }

        return false;
    }

    // Exclui uma pessoa com base no ID
    public boolean excluirPessoa(int id) {

        Pessoa pessoa = buscarPessoaPorId(id);

        if (pessoa != null) {

            pessoas.remove(pessoa);

            pessoaRepository.salvarPessoas(pessoas);

            return true;
        }

        return false;
    }

    // Método auxiliar que busca uma pessoa na lista pelo seu ID
    private Pessoa buscarPessoaPorId(int id) {

        for (Pessoa pessoa : pessoas) {

            if (pessoa.getId() == id) {

                return pessoa;
            }
        }

        return null;
    }
}
