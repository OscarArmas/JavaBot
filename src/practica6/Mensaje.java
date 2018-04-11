package practica6;

public class Mensaje {
     private String texto;
    private int id;
    public Mensaje(String texto, int id){
        this.id=id;
        this.texto=texto;

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
