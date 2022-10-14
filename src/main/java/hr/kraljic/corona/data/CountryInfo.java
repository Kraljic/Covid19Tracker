package hr.kraljic.corona.data;

public class CountryInfo {
    private String country;
    private int totalCases;
    private int newCases;
    private int totalDeaths;
    private int newDeaths;
    private int totalRecovered;
    private int activeCases;
    private int seriousCases;
    private double totalCasesPer1MPopulation;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getSeriousCases() {
        return seriousCases;
    }

    public void setSeriousCases(int seriousCases) {
        this.seriousCases = seriousCases;
    }

    public double getTotalCasesPer1MPopulation() {
        return totalCasesPer1MPopulation;
    }

    public void setTotalCasesPer1MPopulation(double totalCasesPer1MPopulation) {
        this.totalCasesPer1MPopulation = totalCasesPer1MPopulation;
    }

    @Override
    public String toString() {
        return "CountryStats{" +
                "country='" + country + '\'' +
                ", totalCases=" + totalCases +
                ", newCases=" + newCases +
                ", totalDeaths=" + totalDeaths +
                ", newDeaths=" + newDeaths +
                ", totalRecovered=" + totalRecovered +
                ", activeCases=" + activeCases +
                ", seriousCases=" + seriousCases +
                ", totalCasesPer1MPopulation=" + totalCasesPer1MPopulation +
                '}';
    }
}
