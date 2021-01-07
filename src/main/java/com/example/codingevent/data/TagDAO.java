package com.example.codingevent.data;

import com.example.codingevent.Models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO extends CrudRepository<Tag, Long> {

}
