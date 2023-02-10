# Lucene Demo

Lucene Core is a Java library providing powerful indexing and search features, as well as spellchecking, hit highlighting and advanced analysis/tokenization capabilities.

This repository uses Apache Lucene to create a simple information retrieval system.

## Dependencies

[lucene-core-9.5.0](https://mavenlibs.com/jar/file/org.apache.lucene/lucene-core)

## How to Rrun?
* First run the [NetClientGet.java](), it will fetch data from server.
![image](https://user-images.githubusercontent.com/53792139/218107546-cc18443e-b93f-4603-ae2a-24c6b6f7008e.png)
* After that run [WriteIndex.java](), it will create index for all fetched files.
* Now run [ReadIndex.java](), it will search for the specified keyword in inex.
![image](https://user-images.githubusercontent.com/53792139/218107868-57f5bccd-9286-493d-8099-bc680a6d5c62.png)

## Note
```sh
1) indexFiles in same package is required for running WriteIndex.java.
2) change base path in [constants.java] before running project.
```
