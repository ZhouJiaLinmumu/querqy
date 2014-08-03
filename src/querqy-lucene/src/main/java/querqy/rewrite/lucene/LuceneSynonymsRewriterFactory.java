/**
 * 
 */
package querqy.rewrite.lucene;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Map;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.synonym.SolrSynonymParser;
import org.apache.lucene.analysis.synonym.SynonymMap;

import querqy.model.Query;
import querqy.rewrite.QueryRewriter;
import querqy.rewrite.RewriterFactory;

/**
 * @author rene
 *
 */
public class LuceneSynonymsRewriterFactory implements RewriterFactory {
	
	final SynonymMap synonymMap;
	
	public LuceneSynonymsRewriterFactory(InputStream is) throws IOException {
		SolrSynonymParser parser = new SolrSynonymParser(true, true, new KeywordAnalyzer());
		try {
			parser.parse(new InputStreamReader(is));
		} catch (ParseException e) {
		    throw new IOException(e);
		}
		synonymMap = parser.build();
	}
	
//	public QueryRewriter getRewriter() {
//		return new LuceneSynonymsRewriter(synonymMap);
//	}

    @Override
    public QueryRewriter createRewriter(Query input, Map<String, ?> context) {
        return new LuceneSynonymsRewriter(synonymMap);
    }

}