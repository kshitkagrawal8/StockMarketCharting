package com.kshitiz.companyservice.Exceptions;

public class StockExchangeNotFoundException extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StockExchangeNotFoundException(String message) {
		super();
		this.message = message;
	}

	public StockExchangeNotFoundException() {
		super();
	}
    
}