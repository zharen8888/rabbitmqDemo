package rabbitmqdemo.config.queue;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rabbitmqdemo.config.RabbitNameConfig;

@Configuration
public class QueueConfig {
	
	@Bean
	public Queue queueA(@Qualifier("exchangeDLX") DirectExchange exchange) {
		return QueueBuilder.nonDurable("queueA").expires(1000*60*60)
				.ttl(20000).deadLetterExchange(exchange.getName()).deadLetterRoutingKey("rk-DLX").build();
	}
	
	@Bean
	public Queue queueB(@Qualifier("exchangeDLX") DirectExchange exchange) {	
		return QueueBuilder.nonDurable("queueB").expires(1000*60*60)
				.ttl(40000).deadLetterExchange(exchange.getName()).deadLetterRoutingKey("rk-DLX").build();
	}
	
	@Bean
	public Queue queueC() {	
		return QueueBuilder.nonDurable("queueC").expires(1000*60*60)
				.build();
	}
	
	@Bean
	public Queue queueAck() {	
		return QueueBuilder.nonDurable(RabbitNameConfig.queueAck).expires(1000*60*60)
				.build();
	}
	
	@Bean
	public Queue queueAlterBack() {	
		return QueueBuilder.nonDurable(RabbitNameConfig.queueAlterBack).expires(1000*60*60)
				.build();
	}

}
