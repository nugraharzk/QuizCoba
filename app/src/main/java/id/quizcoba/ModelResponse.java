package id.quizcoba;

public class ModelResponse {

    private String saldo, respon;

    public ModelResponse() {
    }

    public ModelResponse(String saldo, String respon) {
        this.saldo = saldo;
        this.respon = respon;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getRespon() {
        return respon;
    }

    public void setRespon(String respon) {
        this.respon = respon;
    }
}
