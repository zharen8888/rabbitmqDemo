package rabbitmqdemo.config.bind;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rabbitmqdemo.config.RabbitNameConfig;


@Configuration
public class BindingConfig {
	
	@Bean
	public Binding QueueABindingNormal(@Qualifier("exchangeNormal") DirectExchange exchange,
			@Qualifier("queueA") Queue queue) {	
		return BindingBuilder.bind(queue).to(exchange).with("rk-delay-20");
	}
	
	@Bean
	public Binding QueueBBindingNormal(@Qualifier("exchangeNormal") DirectExchange exchange,
			@Qualifier("queueB") Queue queue) {	
		return BindingBuilder.bind(queue).to(exchange).with("rk-delay-40");
	}
	
	@Bean
	public Binding QueueCBindingDLX(@Qualifier("exchangeDLX") DirectExchange exchange,
			@Qualifier("queueC") Queue queue) {	
		return BindingBuilder.bind(queue).to(exchange).with("rk-DLX");
	}
	
	@Bean
	public Binding QueueCBindingDelay(@Qualifier("delayedExchange") CustomExchange exchange,
			@Qualifier("queueC") Queue queue) {	
		return BindingBuilder.bind(queue).to(exchange).with("rk-delay").noargs();
	}
	
	@Bean
	public Binding QueueAckBindingAck(@Qualifier("exchangeAck") DirectExchange exchange,
			@Qualifier(RabbitNameConfig.queueAck) Queue queue) {	
		return BindingBuilder.bind(queue).to(exchange).with("rk-ack");
	}
	

}
