package com.eduonix.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ubu on 14.08.15.
 */
public class RestDocumentManager {

    private static SolrClient solr;

    public static void main(String[] args) {

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();


        URL inputFilePath = RestDocumentManager.class.getResource(CONFIG.SOLR_DATA.getAttribute());

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFilePath.getPath()))) {

            int count = 0;
            String line = reader.readLine();
            docs.add(createSolrDocumentToLoad(line, count));
            while (line != null) {
                docs.add(createSolrDocumentToLoad(line, count));
                line = reader.readLine();
            }

            System.out.println(docs.size());

            String urlString = String.format("%s%s",CONFIG.SOLR_HOST.getAttribute(), CONFIG.SOLR_HOST_INDEX.getAttribute());
//            solr = new HttpSolrClient(urlString);
//
//            solr.add(docs);
//            solr.commit();

            System.out.println("created this many docs on remote solr instance: "  +getNumSolrDocs());


        } catch (IOException e) {
            e.printStackTrace();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
        }

    }


    private static SolrInputDocument createSolrDocumentToLoad(String line, int count) {

        SolrInputDocument doc = new SolrInputDocument();

        String[] tmp = line.split("\t");
        doc.addField("id", count++ );
        System.out.println(tmp.length);
        doc.addField("ncid_s", tmp[0]);
        doc.addField("concept_definition_t", tmp[1]);
        if(tmp.length > 2) {
            doc.addField("cid_t", tmp[2]);
        }


        return doc;

    }


    private static long  getNumSolrDocs()
    {
        StackTraceElement[] trace = new Exception().getStackTrace();
        String sourceMethod = trace[0].getMethodName();
        SolrQuery q = new SolrQuery("*:*");
        q.setRows(0);  // don't actually request any data
        long numDocs=0;
        try{
            numDocs= solr.query(q).getResults().getNumFound();
        }
        catch (Exception e)
        {


        }
        return numDocs;
    }

}
