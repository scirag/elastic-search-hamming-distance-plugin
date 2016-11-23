from elasticsearch import Elasticsearch

ES_INDEX_NAME = 'tr.awakening'
ES_DOC_TYPE = 'image_info'

es = Elasticsearch()


def search_image(phash, min_score):
    search_results = es.search(index=ES_INDEX_NAME, doc_type=ES_DOC_TYPE, body={
        "query": {
            "function_score": {
                "min_score": min_score,
                "query": {
                    "match_all": {}
                },
                "functions": [
                    {
                        "script_score": {
                            "script": "hamming_distance",
                            "lang": "native",
                            "params": {
                                "hash": phash,
                                "field": "hash"
                            }
                        }
                    }
                ]
            }
        }
    })

    return search_results
	