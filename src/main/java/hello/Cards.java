package hello;

public class Cards {
	
    private int id;
    private String cardNumber;
    private String name;
    private short expDate;

    public Cards() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    /**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setExpDate(short expDate) {
        this.expDate = expDate;
    }
    
    public short getExpDate() {
    	return expDate;
    }
    
}
