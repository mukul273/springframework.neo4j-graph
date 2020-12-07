package com.springframework.neo4jgraph.repository;

import com.springframework.neo4jgraph.node.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book, String> {
    List<Book> findByName(String name);
}
