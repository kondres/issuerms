package com.amazingbooks.issuerms.repo;

import com.amazingbooks.issuerms.model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
}
