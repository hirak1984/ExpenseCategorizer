package pt.hrk.model;

import java.util.Date;

import javax.money.MonetaryAmount;

public class Transaction {
private Date postedDate;
private String payee;
private MonetaryAmount amount;
private String category;
private String subCategory;

public  Transaction(Date postedDate, String payee, MonetaryAmount amount) {
	super();
	this.postedDate = postedDate;
	this.payee = payee;
	this.amount = amount;
}
public Date getPostedDate() {
	return postedDate;
}
public String getPayee() {
	return payee;
}
public MonetaryAmount getAmount() {
	return amount;
}
public String getCategory() {
	return category==null?"Unclassified":category;
}
public String getSubCategory() {
	return subCategory==null?"Unclassified":subCategory;
}
public void setCategory(String category) {
	this.category = category;
}
public void setSubCategory(String subCategory) {
	this.subCategory = subCategory;
}


}
