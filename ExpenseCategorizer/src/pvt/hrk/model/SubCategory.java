package pvt.hrk.model;

import java.util.List;

public class SubCategory {
	public String name;
	public List<String> keywordContainsIgnoreCase;
	@Override
	public String toString() {
		return "SubCategory [name=" + name + ", keywordContainsIgnoreCase=" + keywordContainsIgnoreCase + "]";
	}

}
