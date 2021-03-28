package Model;

public class Endereco {
    private String identificacaoEndereco;
    private String pais, cep, rua, complemento, bairro, cidade, uf;
    private int numero;

    public Endereco(String identificacaoEndereco, String pais, String cep, String rua, int numero, String complemento, String bairro, String cidade, String uf) {
        this.identificacaoEndereco = identificacaoEndereco;
        this.pais = pais;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getIdentificacaoEndereco() {
        return identificacaoEndereco;
    }

    public void setIdentificacaoEndereco(String identificacaoEndereco) {
        this.identificacaoEndereco = identificacaoEndereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void imprimir(){
        System.out.println("Identificação do Endereço: "+identificacaoEndereco+"\nPaís: "+pais+"\nCEP: "+cep
                +"\nRua: "+rua+"\nNumero:"+numero+"\nComplemento: "+complemento
                +"\nBairro: "+bairro+"\nCidade: "+cidade+"\nEstado/UF: "+uf);
    }
  
    
}
