// =============================================
// MÓDULO: Dados.java
// Responsabilidade: Estruturas de dados do sistema
// =============================================
 
class Mecanico {
    int codigo;
    String nome;
    String especialidade;
}
 
class Veiculo {
    String placa;
    String nomeDono;
    String modelo;
}
 
class Peca {
    int codigo;
    String descricao;
    int quantidade;
    double precoUnitario;
}
 
class OrdemServico {
    int numero;
    String placaVeiculo;
    int codigoMecanico;
    int codigoPeca;
    int quantidadePeca;
    double valorMaoDeObra;
}