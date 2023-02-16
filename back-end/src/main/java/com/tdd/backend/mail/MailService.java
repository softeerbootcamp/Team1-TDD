package com.tdd.backend.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	public void sendMail(EmailMessage emailMessage) throws MailSendException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
			mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
			mimeMessageHelper.setText(emailMessage.getMessage(), false); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);
			log.info("Success!!");
		} catch (MessagingException e) {
			log.info("fail!!");
			throw new RuntimeException(e);
		}
	}
}
