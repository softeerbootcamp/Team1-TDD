package com.tdd.backend.mail.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tdd.backend.mail.data.EmailMessage;
import com.tdd.backend.mail.data.PostInfo;
import com.tdd.backend.mail.data.UserType;
import com.tdd.backend.mail.exception.EmailNotFoundException;
import com.tdd.backend.mypage.exception.PostNotFoundException;
import com.tdd.backend.post.repository.PostRepository;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public void send(Long appointmentId, Long testerId) {
		PostInfo postInfo = postRepository.findPostInfoByAppointmentId(appointmentId)
			.orElseThrow(PostNotFoundException::new);
		EmailMessage ownerEmailMessage = generateEmailMessage(postInfo, testerId, postInfo.getUserId(),
			UserType.OWNER);
		EmailMessage testerEmailMessage = generateEmailMessage(postInfo, postInfo.getUserId(), testerId,
			UserType.TESTER);

		senMimeMail(ownerEmailMessage);
		senMimeMail(testerEmailMessage);
		log.info("Sent simple email!");
	}

	private void senMimeMail(EmailMessage emailMessage) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			mimeMessage.setText(emailMessage.getMessage(), "utf-8", "html");
			mimeMessage.addRecipients(Message.RecipientType.TO, emailMessage.getTo());
			mimeMessage.setSubject(emailMessage.getSubject());
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public EmailMessage generateEmailMessage(PostInfo postInfo, Long anotherId, Long receiverId, UserType userType) {
		String toEmail = userRepository.findEmailById(receiverId)
			.orElseThrow(EmailNotFoundException::new);
		User another = userRepository.findById(anotherId)
			.orElseThrow(UserNotFoundException::new);

		return EmailMessage.builder()
			.to(toEmail)
			.subject(userType.getSubject())
			.carName(postInfo.getCarName())
			.date(postInfo.getDate())
			.requirement(postInfo.getRequirement())
			.email(another.getEmail())
			.name(another.getUserName())
			.userType(userType)
			.phoneNumber(another.getPhoneNumber())
			.build();
	}
}
