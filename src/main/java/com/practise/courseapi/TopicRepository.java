package com.practise.courseapi;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
//@N1qlPrimaryIndexed
//@ViewIndexed(designDoc = "course")
public interface TopicRepository extends CouchbaseRepository<Topic, String> {
}
