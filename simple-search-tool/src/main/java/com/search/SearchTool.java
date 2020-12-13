package com.search;

import java.util.List;

public interface SearchTool {

	List<Hit> search(String query, boolean complete);

	void index(Document doc);

}
