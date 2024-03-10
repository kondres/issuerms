import com.amazingbooks.issuerms.dto.BookIssueRequest;
import com.amazingbooks.issuerms.service.BookIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/book-issues")
public class BookIssueController {

    @Autowired
    private BookIssueService bookIssueService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/issue")
    public ResponseEntity<?> issueBookToCustomer(@RequestBody BookIssueRequest request) {
        try {
            bookIssueService.issueBook(request);
            return new ResponseEntity<>("Book issued successfully.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
