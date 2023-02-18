package com.tdd.backend.mail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.tdd.backend.mail.service.MailService;
import com.tdd.backend.post.service.DrivingService.AppointmentAcceptEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventListener {

	private final MailService mailService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = AppointmentAcceptEvent.class)
	public void handle(AppointmentAcceptEvent event) {
		Long appointmentId = event.getAppointmentId();
		Long testerId = event.getTesterId();

		mailService.send(appointmentId, testerId);
	}

}
