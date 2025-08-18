Rest API который принимает измерения сенсора и сохраняет их в БД.

Стек:

Spring boot,
Spring web,
PostgreSQL
Apache Kafka
Redis
Liquibase
Mapstruct
Lombok

Реализованно:

Entity и DTO(Sensor, Measurement).

Validation: 
данных с глобальным Handler обработчиком.

Liquibase: 
для автоматического накатывания структуры БД.

Kafka: 
MeasurementRestController(producer) -> KafkaConsumer @Listener вызывает MeasurementService

Redis:
Перед добавление нового Measurement идет проверка существования Sensor в БД.
Чтобы уменьшить колличесвто обращений к БД, поместил Sensor в кеш 2 уровня.

Swagger:
доступен по стандартному адрессу http://localhost:8080/swagger-ui/



