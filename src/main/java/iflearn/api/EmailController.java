package iflearn.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
	private final EmailService es;

	public EmailController(EmailService es) {
		this.es = es;
	}

	@PostMapping("/envia")
	@ResponseStatus(HttpStatus.CREATED)
	public void envia(@RequestBody EmailRequest emaildto) {
		es.envia(emaildto);
	}

}
