package rabbitmqdemo.config.consumer;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import rabbitmqdemo.config.RabbitNameConfig;
import rabbitmqdemo.pojo.Msg;

@Slf4j
@Service
@RabbitListener(queues = RabbitNameConfig.queueAck,containerFactory = "singleListenerContainer")
public class ConsumerFactoryListener {
	
	public ConsumerFactoryListener() {
		log.info("===========ConsumerFactoryListener()=========");
	}
	
	@RabbitHandler(isDefault = true)
	public void handle1(@Payload String ob1,Channel channel,@Headers Map<String,Object> map) throws IOException {
		log.info("!!!!!!listener get1 !!!!!!!!!!!!"+ob1.toString());
		channel.basicAck((long) map.get(AmqpHeaders.DELIVERY_TAG), false);
	}
	
	
	@RabbitHandler
	public void handle3(@Payload Msg msg,Channel channel,@Headers Map<String,Object> map) throws IOException {
		log.info("!!!!!!listener get3 !!!!!!!!!!!!"+msg.toString());
		channel.basicAck((long) map.get(AmqpHeaders.DELIVERY_TAG), false);
	}

}
