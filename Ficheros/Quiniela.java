import java.io.Serializable;

public class Quiniela implements Serializable {
    private String equ1;
    private String equ2;
    private char resul;


    public Quiniela(String equ1, String equ2, char resul) {
        this.equ1 = equ1;
        this.equ2 = equ2;
        this.resul = resul;
    }


    public String getEqu1() {
        return equ1;
    }


    public void setEqu1(String equ1) {
        this.equ1 = equ1;
    }


    public String getEqu2() {
        return equ2;
    }


    public void setEqu2(String equ2) {
        this.equ2 = equ2;
    }


    public char getResul() {
        return resul;
    }


    public void setResul(char resul) {
        this.resul = resul;
    }


    @Override
    public String toString() {
        return "Quiniela [equ1=" + equ1 + ", equ2=" + equ2 + ", resul=" + resul + "]";
    }
}
