package rabbitmqdemo.config.exchange;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rabbitmqdemo.config.RabbitNameConfig;


@Configuration
public class ExchangeConfig {
	
	@Bean
	public DirectExchange exchangeNormal() {	
		return ExchangeBuilder.directExchange("normal").autoDelete()
				.durable(false).build();
	}
	
	@Bean
	public DirectExchange exchangeDLX() {	
		return ExchangeBuilder.directExchange("DLX").autoDelete()
				.durable(false).build();
	}
	
	
	
	//基于插件的延迟队列
	@Bean
	public CustomExchange delayedExchange() {	
		Map<String,Object> args = new HashMap<String,Object>(); 
		args.put("x-delayed-type", "direct");
		return new CustomExchange("plugin-delay", "x-delayed-message", true, true, args);
	}
	
	@Bean
	public DirectExchange exchangeAck() {	
		return ExchangeBuilder.directExchange(RabbitNameConfig.EX_ACK).autoDelete().alternate(RabbitNameConfig.EX_ALTERNATE)
				.durable(false).build();
	}
	
	//备份交换机alternate-ex
	@Bean
	public FanoutExchange alternateExchange() {	
		return ExchangeBuilder.fanoutExchange(RabbitNameConfig.EX_ALTERNATE).autoDelete()
				.durable(false).build();
	}
	
	
	

}
