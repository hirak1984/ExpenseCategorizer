package pvt.hrk.model;

import java.util.List;

public class Category {
	public String name;
	public List<SubCategory> subCategories;
	@Override
	public String toString() {
		return "Category [name=" + name + ", subCategories=" + subCategories + "]";
	}
	
}
