public class Local {
    private String nome;
    private int capacidadeMaxima;
    
    public Local(String nome, int capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    @Override
    public String toString() {
        return nome + " (Capacidade: " + capacidadeMaxima + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Local local = (Local) obj;
        return nome.equals(local.nome);
    }
}

