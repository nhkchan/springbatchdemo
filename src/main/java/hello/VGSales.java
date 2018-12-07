package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VGSALES")
public class VGSales {
	
	@Id
	@Column(name="rank", nullable = true, unique = false)
    private String rank;
	@Column(name="name", nullable = true, unique = false)
    private String name;
	@Column(name="platform", nullable = true, unique = false)
    private String platform;
	@Column(name="year", nullable = true, unique = false)
    private String year;
	@Column(name="genre", nullable = true, unique = false)
    private String genre;
	@Column(name="publisher", nullable = true, unique = false)
    private String publisher;
	@Column(name="naSales", nullable = true, unique = false)
    private double naSales;
	@Column(name="euSales", nullable = true, unique = false)
    private double euSales;
	@Column(name="jpSales", nullable = true, unique = false)
    private double jpSales;
	@Column(name="otherSales", nullable = true, unique = false)
    private double otherSales;
	@Column(name="globalSales", nullable = true, unique = false)
    private double globalSales;

    public VGSales() {
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }
    
    public void setNaSales(double naSales) {
        this.naSales = naSales;
    }

    public double getNaSales() {
        return naSales;
    }
    
    public void setEuSales(double euSales) {
        this.euSales = euSales;
    }

    public double getEuSales() {
        return euSales;
    }
    
    public void setJpSales(double jpSales) {
        this.jpSales = jpSales;
    }

    public double getJpSales() {
        return jpSales;
    }

    public void setOtherSales(double otherSales) {
        this.otherSales = otherSales;
    }

    public double getOtherSales() {
        return otherSales;
    }
    
    public void setGlobalSales(double globalSales) {
        this.globalSales = globalSales;
    }

    public double getGlobalSales() {
        return globalSales;
    }
    
    
}
