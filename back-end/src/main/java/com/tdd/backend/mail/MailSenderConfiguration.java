package com.tdd.backend.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.RequiredArgsConstructor;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "spring.mail")
@RequiredArgsConstructor
public class MailSenderConfiguration {
	@Value("${spring.mail.properties.subject.member.registration}") //"Thank you for joining our cafe!" 메시지 주입
	private String subject;
	private final JavaMailSender emailSender;

}

