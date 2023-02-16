package com.tdd.backend.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tdd.backend.mail.data.EmailMessage;
import com.tdd.backend.mail.data.EmailMessage.UserType;
import com.tdd.backend.mail.data.PostInfo;
import com.tdd.backend.post.repository.PostRepository;
import com.tdd.backend.user.data.User;
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
		PostInfo postInfo = postRepository.findPostInfoByAppointmentId(appointmentId).orElseThrow();
		EmailMessage ownerEmailMessage = generateEmailMessage(postInfo, testerId, postInfo.getUserId(),
			UserType.TESTER);
		EmailMessage testerEmailMessage = generateEmailMessage(postInfo, postInfo.getUserId(), testerId,
			UserType.OWNER);

		sendSimpleMail(ownerEmailMessage);
		sendSimpleMail(testerEmailMessage);
		log.info("Sent simple email!");
	}

	private void sendSimpleMail(EmailMessage emailMessage) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(emailMessage.getTo());
		simpleMailMessage.setSubject(emailMessage.getSubject());
		simpleMailMessage.setText(emailMessage.getMessage());
		javaMailSender.send(simpleMailMessage);
	}

	public EmailMessage generateEmailMessage(PostInfo postInfo, Long anotherId, Long receiverId, UserType userType) {
		String toEmail = userRepository.findEmailById(receiverId).orElseThrow();
		User another = userRepository.findById(anotherId).orElseThrow();

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
