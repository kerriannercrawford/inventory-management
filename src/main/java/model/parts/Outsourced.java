package model.parts;

/**
 * Extends Part - Outsourced Parts
 */
public class Outsourced extends Part {
    /**
     * Company name
     */
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.setCompanyName(companyName);
    }

    /**
     * set company name
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * get company name
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
