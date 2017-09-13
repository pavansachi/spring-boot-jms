package org.jms;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class JMSPublisherTest2 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
//		context.close();
	}

	@Autowired
	private JmsTemplate jmsTemplate;
	
//	@Autowired
//	ConfigurableApplicationContext context;
	
	@Autowired
	Receiver receiver;
	
	@Test
	public void publishToRabbitMQ2 () throws InterruptedException {
		
		 jmsTemplate.convertAndSend("testQueue", "Hello ActiveMQ");
		 
		 receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}
	
}
