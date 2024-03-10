package com.amazingbooks.issuerms.service;

import com.amazingbooks.issuerms.dto.BookIssueRequest;
import com.amazingbooks.issuerms.model.Book;
import com.amazingbooks.issuerms.model.BookIssue;
import com.amazingbooks.issuerms.repo.BookIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BookIssueService {

    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate to make REST calls to bookms

    public void issueBook(BookIssueRequest request) {
        // Fetch book details from bookms
        Book book = restTemplate.getForObject("http://bookms//fetchBook/{isbn}", Book.class, request.getIsbn());
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + request.getIsbn() + " not found.");
        }

        int availableCopies = book.getTotalCopies() - book.getIssuedCopies();
        if (availableCopies < request.getNoOfCopies()) {
            throw new IllegalArgumentException("Not enough available copies of the book.");
        }

        // Update issuedCopies in bookms
        book.setIssuedCopies(book.getIssuedCopies() + request.getNoOfCopies());
        restTemplate.put("http://bookms/api/books/{isbn}", book, request.getIsbn());

        // Issue the book to customer
        BookIssue bookIssue = new BookIssue();
        bookIssue.setIsbn(request.getIsbn());
        bookIssue.setCuspId(request.getCuspId());
        bookIssue.setNoOfCopies(request.getNoOfCopies());
        bookIssueRepository.save(bookIssue);
    }
}
