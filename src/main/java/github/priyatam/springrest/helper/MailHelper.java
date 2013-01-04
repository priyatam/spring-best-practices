package github.priyatam.springrest.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class MailHelper {

	final Logger logger = LoggerFactory.getLogger(MailHelper.class);

	@Inject
	private MailSender mailSender;

	// Flag to turn off email (useful for unit testing)
	private boolean isMailEnabled = true;

	@Async
	public void sendAsyncEmail(final String from, final String to, final String subject, final String body) {
		logger.info(Thread.currentThread().getName() + " begin asynchronous process ...");
		sendMail(from, to, subject, body);
	}

	public void sendMail(final String from, final String to, final String subject, final String body) {
		if (isMailEnabled()) {
			logger.info("Sending email: " + body);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(from);
			msg.setTo(to);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(body);
			try {
				mailSender.send(msg);
			} catch (final MailException ex) {
				logger.error("Error while sending mail: " + ex.getMessage());
			}
		} else {
			logger.warn("Mail is not enabled. Check if isMailEnabled is set to false in your spring config!");
			return;
		}
	}

	public boolean isMailEnabled() {
		return isMailEnabled;
	}

	public void setMailEnabled(boolean mailEnabled) {
		isMailEnabled = mailEnabled;
	}
}
