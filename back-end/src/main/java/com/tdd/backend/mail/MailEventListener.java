package com.tdd.backend.mail;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tdd.backend.mail.service.MailService;
import com.tdd.backend.post.service.DrivingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailEventListener {

	private final MailService mailService;

	@Async("mailExecutor")
	@EventListener
	public void handle(DrivingService.AppointmentMailEvent event) {
		mailService.send(event.getAppointmentId(), event.getTesterId());
	}
}
