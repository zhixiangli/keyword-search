keyword-search
===============


**Keyword Search** is implemented with Aho-Corasick Automaton, with a case of mobile detect.

Time Complexity: O(n), faster than other algorithms, such as indexOf(). 


## Environmental Requirements

JDK 1.8+

## Instructions

### Add Dependency
	<dependency>
		<groupId>com.zhixiangli</groupId>
		<artifactId>keyword-search</artifactId>
		<version>0.0.1</version>
	</dependency>

### Example
    KeywordSearch keywordSearch = new KeywordSearch();
    // [must] add all keyword at one time.
    keywordSearch.addAll(new String[] {"a", "bc", "def"});
    System.out.println(keywordSearch.contains("bef")); // false
    System.out.println(keywordSearch.contains("bobcome")); // true