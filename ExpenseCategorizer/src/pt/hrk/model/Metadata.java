package pt.hrk.model;

import java.util.LinkedList;
import java.util.List;

public class Metadata {
	public List<Category> categories = new LinkedList<>();

	private Category getCategoryByName(String name) {
		if (categories == null) {
			return null;
		}
		return categories.stream().filter(category -> category.name.equals(name)).findFirst().orElse(null);
	}

	public void addSubCategoryToCategory(String categoryName, SubCategory sc) {
		Category cat = getCategoryByName(categoryName);
		if (cat == null) {
			cat = new Category();
			cat.name = categoryName;
		} else {
			categories.remove(cat);
		}

		List<SubCategory> currentSubcategories = cat.subCategories;
		if (currentSubcategories == null) {
			cat.subCategories = new LinkedList<SubCategory>();

		}
		cat.subCategories.add(sc);
		categories.add(cat);
	}

	@Override
	public String toString() {
		return "Metadata [categories=" + categories + "]";
	}

}
