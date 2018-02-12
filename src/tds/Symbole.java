package tds;

public class Symbole {
    private String type;
    private int dep;
    private int NRegion;
    private int NImbric;
    private String[] typeParamFonctions;
    private String retour;

    public Symbole(String type, int dep, int NRegion, int NImbric, String[] typeParamFonctions, String retour) {
        this.type = type;
        this.dep = dep;
        this.NRegion = NRegion;
        this.NImbric = NImbric;
        this.typeParamFonctions = typeParamFonctions;
        this.retour = retour;
    }

    public String getType() {
        return type;
    }

    public int getDep() {
        return dep;
    }
}
