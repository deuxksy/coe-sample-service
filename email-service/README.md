
# Require Software

### RabbitMQ on docker
~~~
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=username -e RABBITMQ_DEFAULT_PASS=password rabbitmq:management
~~~

### [FakeSMTP](http://nilhcem.com/FakeSMTP/download.html)
~~~
Running Command : java -jar fakeSMTP-2.0.jar
~~~
