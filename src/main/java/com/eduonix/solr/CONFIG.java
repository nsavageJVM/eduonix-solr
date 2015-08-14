package com.eduonix.solr;

/**
 * Created by ubu on 14.08.15.
 */
public enum CONFIG {

    SOLR_DATA("/temp.tsv"),
    SOLR_HOST("http://localhost:8983/solr/"),
    SOLR_HOST_INDEX("concepts_index");


    private   String gridAttribute;

    CONFIG(String attribute) {
        this.gridAttribute = attribute;
    }

    public String getAttribute() {
        return gridAttribute;
    }
}
