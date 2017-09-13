package org.jms;

import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Receiver {

	private long time;

	public Receiver() {

	}

	@PostConstruct
	public void init() {

		this.time = System.currentTimeMillis();
		System.out.println("***********Receiver*****************");
		System.out.println(time);
	}

	public long getTime() {
		return time;
	}


	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	private String message = null;

	public String getMessage() {
		return message;
	}

	@JmsListener(destination = "testQueue", containerFactory = "myFactory")
	public void receiveMessage(String data) {
		System.out.println("Received <" + data + ">");
		latch.countDown();
	}

}