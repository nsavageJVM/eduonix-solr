package com.eduonix.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;

/**
 * Created by ubu on 14.08.15.
 */
public class RestQueryManager {

    private static SolrClient solr;


    public static void main(String[] args) {


        String urlString = String.format("%s%s",CONFIG.SOLR_HOST.getAttribute(), CONFIG.SOLR_HOST_INDEX.getAttribute());
        solr = new HttpSolrClient(urlString);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery( "concept_definition_t:Newborn" );
        solrQuery.setFacet(true);
        solrQuery.setHighlight(true).setHighlightSnippets(1);

        QueryResponse rsp=null;

        try {
            rsp = solr.query(solrQuery);
            SolrDocumentList docs = rsp.getResults();
            Iterator<SolrDocument> iterDocs=docs.iterator();
            while(iterDocs.hasNext())
            {
                SolrDocument doc = iterDocs.next();
                System.out.println("ncid value for search term \'Newborn\': " + doc.getFieldValue("ncid_s"));

            }


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
