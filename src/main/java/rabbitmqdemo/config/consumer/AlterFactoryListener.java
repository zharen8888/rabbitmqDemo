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
@RabbitListener(queues = RabbitNameConfig.queueAlterBack,containerFactory = "singleListenerContainer")
public class AlterFactoryListener {
	
	@RabbitHandler(isDefault = true)
	public void handle1(@Payload Object ob1,Channel channel,@Headers Map<String,Object> map) throws IOException {
		log.info("!!!!!!AlterFactoryListener get1 !!!!!!!!!!!!"+ob1.toString());
		channel.basicAck((long) map.get(AmqpHeaders.DELIVERY_TAG), false);
	}
	
	//@RabbitHandler
	public void handle1(@Payload Msg ob1,Channel channel,@Headers Map<String,Object> map) throws IOException {
		log.info("!!!!!!AlterFactoryListener get2 !!!!!!!!!!!!"+ob1.toString());
		channel.basicAck((long) map.get(AmqpHeaders.DELIVERY_TAG), false);
	}
	

}
