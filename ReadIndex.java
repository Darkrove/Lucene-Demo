package LuceneDemo;

import java.io.IOException;
import java.nio.file.Paths;
 
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
 
public class ReadIndex
{ 
    static String queyString = "Next.js";
    public static void main(String[] args) throws Exception
    {
        IndexSearcher searcher = createSearcher();
        TopDocs foundDocs = searchInContent(queyString, searcher);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Query Passed :: " + queyString);
        System.out.println("Total Results :: " + foundDocs.totalHits);
        for (ScoreDoc sd : foundDocs.scoreDocs)
        {
            Document d = searcher.doc(sd.doc);
            System.out.println("Path :: "+ d.get("path") + ", Score : " + sd.score);
        }
        System.out.println("--------------------------------------------------------------------");
    }
     
    private static TopDocs searchInContent(String textToFind, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
        Query query = qp.parse(textToFind);
        TopDocs hits = searcher.search(query, 10);
        return hits;
    }
 
    private static IndexSearcher createSearcher() throws IOException
    {
        Directory dir = FSDirectory.open(Paths.get(Constants.BASEPATH + Constants.INDEXPATH));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }
}
