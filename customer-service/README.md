# Customer Service

## Description

본 프로젝트는 Microservice를 위한 Sample Project 입니다.


## 설정방법

1. Dependency를 추가한다.
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
```

2. application.yml에 rabbitmq 설정 정보를 추가한다.
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: username
    password: password
```

3. Create a Message Sender
```java
    import com.sds.act.coe.customer.config.MessageQueueConfig;
    import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
    import org.springframework.context.annotation.Lazy;
    import org.springframework.stereotype.Component;

    @Component
    @Lazy
    public class Sender {

        RabbitMessagingTemplate template;

        public Sender(RabbitMessagingTemplate template) {
            this.template = template;
        }

        public void send(String message) {
            template.convertAndSend(MessageQueueConfig.QUEUE_NAME, message);
        }
    }
```

4. Create a Message Receiver - [Email Service](https://github.com/SDSACT/coe-email-service/blob/master/README.md)
```java
    import com.sds.act.coe.customer.config.MessageQueueConfig;
    import org.springframework.amqp.rabbit.annotation.RabbitListener;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    @Component
    public class Receiver {
        private Mailer mailer;

        @Autowired
        public Receiver(Mailer mailer) {
            this.mailer = mailer;
        }

        @RabbitListener(queues = MessageQueueConfig.QUEUE_NAME)
        public void processMessage(String email) {
            System.out.println("get Message:" + email);
            mailer.sendMail(email);
        }
    }
```

## Docker Push 방법

#### [First Step] Login to Repository  
~~~sh
docker login {{REPOSITORY_URL}} -u {{USERNAME}} -p {{PASSWORD}}
~~~
Result :
~~~sh
WARNING! Using --password via the CLI is insecure. Use --password-stdin.
Login Succeeded
~~~
#### [Second Step] BUILD Docker Images
~~~sh
docker build -t customer-service .
~~~
#### [Third Step] Tag Docker Images
~~~sh
docker tag customer-service {{REPOSITORY_URL:PORT}}/customer-service
~~~
#### [Forth Step] Push Docker Repository
~~~sh
docker push {{REPOSITORY_URL:PORT}}/customer-service
~~~

## 999. Require Software

### RabbitMQ on docker
~~~
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=username -e RABBITMQ_DEFAULT_PASS=password rabbitmq:management
~~~

~~~
docker run -d --name rabbitmq1 -p 5672:5672 -p 15672:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=username -e RABBITMQ_DEFAULT_PASS=password rabbitmq:management

docker run -d --name rabbitmq2 -p 5673:5672 -p 15673:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=username -e RABBITMQ_DEFAULT_PASS=password rabbitmq:management
~~~
