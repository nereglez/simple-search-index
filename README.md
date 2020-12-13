# Description 

The task is to create a simple in memory search tool from scratch.
There is an interface which defines two methods:

```java
    void index(Document doc);

    List<Hit> search(String query, boolean complete);
```

The first method simply adds an instance of com.search.Document to the collection of searchable documents. 

The second method evaluates the collection of searchable documents against the given query, and returns a ranked list of
matching documents. A document is said to match a query if each word of the query (or at least one of the words, if complete is 
false) can be found on at least one of the documents properties. Words are whitespace separated, and the search should
be case-insensitive.

The ranking definition is not strict, but should at least ensure that a document that matches more words of the query should be
ranked higher than a document that matches less words of the query.


*Nowadays incomplete exercise*

