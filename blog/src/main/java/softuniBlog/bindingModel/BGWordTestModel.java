package softuniBlog.bindingModel;


import javax.validation.constraints.NotNull;

public class BGWordTestModel {

    private String AA;
    private String BB;
    private String CC;
    private String DD;
    private String EE;

    @NotNull
    private String A;
    @NotNull
    private String B;
    @NotNull
    private String C;
    @NotNull
    private String D;
    @NotNull
    private String E;

    public String getAA() {return AA;}
    public void setAA(String AA) {this.AA = AA;}
    public String getBB() {return BB;}
    public void setBB(String BB) {this.BB = BB;}
    public String getCC() {return CC;}
    public void setCC(String CC) {this.CC = CC;}
    public String getDD() {return DD;}
    public void setDD(String DD) {this.DD = DD;}
    public String getEE() {return EE;}
    public void setEE(String EE) {this.EE = EE;}
    public String getA() {return A;}
    public void setA(String a) {A = a;}
    public String getB() {return B;}
    public void setB(String b) {B = b;}
    public String getC() {return C;}
    public void setC(String c) {C = c;}
    public String getD() {return D;}
    public void setD(String d) {D = d;}
    public String getE() {return E;}
    public void setE(String e) {E = e;}

    public String getEverythingAsString(){

       String result=this.getAA();
        result+=","+this.getA();
        result+=","+this.getBB();
        result+=","+this.getB();
        result+=","+this.getCC();
        result+=","+this.getC();
        result+=","+this.getDD();
        result+=","+this.getD();
        result+=","+this.getEE();
        result+=","+this.getE();
        return result;
    }


}
