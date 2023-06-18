package rabbitmqdemo.config.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Slf4j
@Configuration
public class ConsumerFactory {
	
	@Bean( name = "singleListenerContainer" )
	public SimpleRabbitListenerContainerFactory listenerContainerFactory(CachingConnectionFactory connectionFactory)
	{
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    /* setConnectionFactory：设置spring-amqp的ConnectionFactory。 */
	    factory.setConnectionFactory( connectionFactory);
	    /* 消息序列化类型 */
	    factory.setMessageConverter( new Jackson2JsonMessageConverter() );
	    /* setConcurrentConsumers：设置每个MessageListenerContainer将会创建的Consumer的最小数量，默认是1个。 */
	    factory.setConcurrentConsumers( 1 );
	    factory.setMaxConcurrentConsumers( 5 );
	    /* setPrefetchCount：设置每次请求发送给每个Consumer的消息数量。 */
	    //factory.setPrefetchCount( 1 );
	    /* 是否设置Channel的事务。 */
	    factory.setChannelTransacted( false );
	    /* 设置当rabbitmq收到nack/reject确认信息时的处理方式，设为true，扔回queue头部，设为false，丢弃。 */
	    factory.setDefaultRequeueRejected( true );
	    /* setErrorHandler：实现ErrorHandler接口设置进去，所有未catch的异常都会由ErrorHandler处理。 */
	    factory.setErrorHandler(e ->{
	    	log.error("=======err handler=======:"+e.getMessage());
	    });
	    factory.setAcknowledgeMode( AcknowledgeMode.MANUAL );
	    return(factory);
	}
	
	
}
