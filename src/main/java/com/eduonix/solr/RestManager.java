package com.eduonix.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

/**
 * Created by ubu on 14.08.15.
 */
public class RestManager {

    public static void main(String[] args) {

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();


        URL inputFilePath = RestManager.class.getResource("/temp.tsv");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFilePath.getPath()))) {

            String line = reader.readLine();
            docs.add(createSolrDocumentToLoad(line));
            while (line != null) {
                docs.add(createSolrDocumentToLoad(line));
                line = reader.readLine();
            }


            System.out.println(docs);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static SolrInputDocument createSolrDocumentToLoad(String line ) {

        SolrInputDocument doc = new SolrInputDocument();

        String[] tmp = line.split("\t");

        doc.addField("ncid", tmp[0]);
        doc.addField("concept_definition", tmp[1]);
        doc.addField("cid", tmp[2]);

        return doc;

    }

}
