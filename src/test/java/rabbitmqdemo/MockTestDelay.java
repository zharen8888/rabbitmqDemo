package rabbitmqdemo;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import rabbitmqdemo.config.RabbitNameConfig;
import rabbitmqdemo.pojo.Msg;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class MockTestDelay {
 
 
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	DirectExchange exchangeNormal;
	
	@Autowired
	CustomExchange delayedExchange;
//    @Before
//    public void setUp() throws Exception {
//        log.info("Before");
//    }
 
    @Test
    public void saveProjectBase() {
    	CorrelationData d = new CorrelationData();
    	//rabbitTemplate.convertSendAndReceive(exchangeNormal.getName(),"rk-delay-20", "i want to send 20s delay", d);
    	rabbitTemplate.convertSendAndReceive(delayedExchange.getName(),"rk-delay", "i want to send  plugin delay 20s, time :"+(new Date()), msg->{
    		msg.getMessageProperties().setDelay(200000);
    		return msg;
    	},d);
    	
    	rabbitTemplate.convertSendAndReceive(delayedExchange.getName(),"rk-delay", "i want to send  plugin delay 20s, time :"+(new Date()), msg->{
    		msg.getMessageProperties().setDelay(200000);
    		return msg;
    	},d);
    	
    	rabbitTemplate.convertSendAndReceive(delayedExchange.getName(),"rk-delay", "i want to send  plugin delay 40s, time :"+(new Date()), msg->{
    		msg.getMessageProperties().setDelay(400000);
    		return msg;
    	},d);
    	
    	rabbitTemplate.convertSendAndReceive(delayedExchange.getName(),"rk-delay", "i want to send  plugin delay 20s, time :"+(new Date()), msg->{
    		msg.getMessageProperties().setDelay(200000);
    		return msg;
    	},d);
    	
    	rabbitTemplate.convertSendAndReceive(delayedExchange.getName(),"rk-delay", "i want to send  plugin delay 40s, time :"+(new Date()), msg->{
    		msg.getMessageProperties().setDelay(400000);
    		return msg;
    	},d);
    	log.info("=====done send========");
    	try {
			Thread.currentThread().sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    
    
    @Test
    public void ack() {
    	rabbitTemplate.setMandatory(true);
    	//(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause
    	rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
    		log.info("\n确认消息送到交换机(Exchange)结果：");
            log.info("相关数据：" + correlationData);
            log.info("是否成功：" + ack);
            log.info("错误原因：" + cause);
    	});
    	
    	rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("\n确认消息送到队列(Queue)结果：");
            log.info("发生消息：" + returnedMessage.getMessage());
            log.info("回应码：" + returnedMessage.getReplyCode());
            log.info("回应信息：" + returnedMessage.getReplyText());
            log.info("交换机：" + returnedMessage.getExchange());
            log.info("路由键：" + returnedMessage.getRoutingKey());
        });
    	
    	//rabbitTemplate.convertAndSend(RabbitNameConfig.EX_ACK,"rk-ack", new Msg("zhangsan","18"));


    	
    	try {
			Thread.currentThread().sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @Test
    public void alterTest() {
    	rabbitTemplate.setMandatory(true);
    	//(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause
    	rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
    		log.info("\n确认消息送到交换机(Exchange)结果：");
            log.info("相关数据：" + correlationData);
            log.info("是否成功：" + ack);
            log.info("错误原因：" + cause);
    	});
    	
    	rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("\n确认消息送到队列(Queue)结果：");
            log.info("发生消息：" + returnedMessage.getMessage());
            log.info("回应码：" + returnedMessage.getReplyCode());
            log.info("回应信息：" + returnedMessage.getReplyText());
            log.info("交换机：" + returnedMessage.getExchange());
            log.info("路由键：" + returnedMessage.getRoutingKey());
        });
    	
    	rabbitTemplate.convertAndSend(RabbitNameConfig.EX_ACK,"rk-ack1", new Msg("zhangsan","18"));

    	
    	try {
			Thread.currentThread().sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}