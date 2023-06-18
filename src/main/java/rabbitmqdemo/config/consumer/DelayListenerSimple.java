package rabbitmqdemo.config.consumer;

import java.io.IOException;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import rabbitmqdemo.config.RabbitNameConfig;


@Slf4j
//@Component
public class DelayListenerSimple {
	
	@RabbitListener(queues = {RabbitNameConfig.queueC})
	public void consumeDelay(Message message) {
		log.info("i receive a msg :{}",new String(message.getBody()));
		System.out.println("!!!!!!!!!!!!!!!!");
	}
	
	@RabbitListener(queues = {RabbitNameConfig.queueAck},ackMode = "MANUAL")
	public void consumeAck1(Message message,	Channel channel) throws IOException, InterruptedException {
		
		Thread.sleep(20000);
		log.info("i receive a msg :{}",new String(message.getBody()));
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		System.out.println("!!!!!!!!!!!!!!!!");
	}
	
	@RabbitListener(queues = {RabbitNameConfig.queueAck},ackMode = "MANUAL")
	public void consumeAck2(Message message,	Channel channel) throws InterruptedException, IOException  {
		Thread.sleep(2000);
		log.info("i receive a msg :{}",new String(message.getBody()));
//		int a =1/0;
		log.info("i receive a msg :{}",new String(message.getBody()));
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		System.out.println("!!!!!!!!!!!!!!!!");
	}
	
	public DelayListenerSimple() {
		System.out.println("!!!!!!!!DelayListener!!!!!!!!");
	}

}
