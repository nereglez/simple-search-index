package com.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchToolImpl implements SearchTool {

	private Map<String, List<Integer>> index = new HashMap<>();

	private Map<Integer, Document> docs = new HashMap<>();

	@Override
	public List<Hit> search(String query, boolean complete) {
		List<Hit> hits;
		List<String> wordsQuery = splitSpace(query);
		List<Integer> foundDocs = new ArrayList<>();
		if (!complete) {
			foundDocs = searchSimple(wordsQuery);
		} else if (index.keySet().containsAll(wordsQuery)) {
			foundDocs = searchExhaustive(wordsQuery);
		}
		hits = calculateOrder(foundDocs);

		return hits;
	}

	private List<Integer> searchExhaustive(List<String> wordsQuery) {
		List<Integer> foundDocs = new ArrayList<>();
		boolean firstTime = true;
		for (String word : wordsQuery) {
			List<Integer> foundDocsByWord = index.get(word);
			if (!firstTime && !foundDocsByWord.containsAll(foundDocs)) {
				foundDocs.retainAll(foundDocsByWord);
			} else if (firstTime){
				firstTime = false;
				foundDocs.addAll(foundDocsByWord);
			}
			
		}
		return foundDocs;
	}

	private List<Integer> searchSimple(List<String> wordsQuery) {
		List<Integer> foundDocs = new ArrayList<>();
		wordsQuery.forEach(word -> {
			List<Integer> foundDocsByWord = index.get(word);
			if (foundDocsByWord != null) {
				foundDocs.addAll(foundDocsByWord);
			}
		});
		return foundDocs;
	}

	private List<Hit> calculateOrder(List<Integer> foundDocs) {
		List<Hit> res = new ArrayList<>();
		Map<Integer, Integer> intermediateMap = new HashMap<>();
		foundDocs.forEach(doc -> intermediateMap.put(doc, intermediateMap.getOrDefault(doc, 0) + 1));
		List<Integer> sortedElements = new ArrayList<>();
		intermediateMap.entrySet().stream()
			.sorted((Map.Entry.comparingByValue()))
				.forEach(entry -> {
					for (int i = 1; i <= entry.getValue(); i++)
						sortedElements.add(entry.getKey());
				});
		docs.values().stream()
			.filter(doc -> sortedElements.contains(doc.getId()))
			.collect(Collectors.toList())
				.forEach(ret -> res.add(new Hit(intermediateMap.get(ret.getId()), ret)));
		return res;

	}

	@Override
	public void index(Document doc) {
		docs.put(doc.getId(), doc);
		addToIndex(doc);
		index.entrySet().forEach(entry -> System.out.println(entry.getKey() + "->" + entry.getValue()));
	}

	private void addToIndex(Document doc) {
		doc.getProperties().values().forEach(prop -> {
			boolean lastWord = false;
			int pos = 0;
			int spacePos = 0;
			while (!lastWord) {
				String word = "";
				spacePos = prop.indexOf(' ', pos);
				if (spacePos >= 0) {
					word = prop.substring(pos, spacePos).toLowerCase().trim();
				} else {
					word = prop.substring(pos).toLowerCase().trim();
					lastWord = true;
				}
				if (index.get(word) != null) {
					index.get(word).add(doc.getId());
				} else {
					List<Integer> docs = new ArrayList<>();
					docs.add(doc.getId());
					index.put(word, docs);
				}
				pos = spacePos + 1;
			}
		});
	}

	private List<String> splitSpace(String text) {
		List<String> result = new ArrayList<String>();
		boolean lastWord = false;
		int pos = 0;
		int spacePos = 0;
		while (!lastWord) {
			String word = "";
			spacePos = text.indexOf(' ', pos);
			if (spacePos >= 0) {
				word = text.substring(pos, spacePos).toLowerCase().trim();
			} else {
				word = text.substring(pos).toLowerCase().trim();
				lastWord = true;
			}
			result.add(word);
			pos = spacePos + 1;
		}
		return result;
	}

}
