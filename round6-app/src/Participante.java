public class Participante {
    private String nome;
    private String numero;
    private int idade;
    
    public Participante(String nome, String numero, int idade) {
        this.nome = nome;
        this.numero = numero;
        this.idade = idade;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public int getIdade() {
        return idade;
    }
    
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    @Override
    public String toString() {
        return "Nº " + numero + " - " + nome + " (" + idade + " anos)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Participante participante = (Participante) obj;
        return numero == participante.numero;
    }
}

