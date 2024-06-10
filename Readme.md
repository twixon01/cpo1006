- Запуск проекта через docker-compose:

1. Переместиться в корень проекта
2. Выполнить команду docker-compose build
3. Выполнить команду docker-compose up -d
! Если Container java-postgres запускается со статусом Error, это нормально, выполните предыдущую команду еще раз


*Важно, выполнять после того как все контейнеры были успешно запущены*
- Добавление информации в таблицу station
1. Заходим в cli postgresql запущенного в контейнере при помощи команды:
docker exec -it java-postgres  psql -U postgres -d ordersdb
2. Вставляем данные в таблицу station:
   INSERT INTO station (station) VALUES
   ('StationA'),
   ('StationB'),
   ('StationC'),
   ('StationD'),
   ('StationE'),
   ('StationF'),
   ('StationG'),
   ('StationO');



- Коллецкия запросов на эндпоинты в postman:
  airlines.postman_collection.json (лежит в корне проекта)


Список использованных технологий:

- Java: Версия 11.
- Spring Framework:
  spring-boot-starter-web: Запуск веб-приложения с Spring MVC.
  spring-boot-starter-data-jpa: Интеграция Spring Data JPA.
  spring-boot-starter-security: Интеграция Spring Security.
  spring-boot-devtools: Инструменты для разработки Spring Boot.
  spring-boot-starter-test: Зависимости для тестирования Spring Boot приложений.
  spring-security-test: Зависимость для тестирования Spring Security.
- PostgreSQL JDBC Driver: 
  Драйвер для работы с PostgreSQL базой данных.
- Project Lombok: 
  Библиотека для автоматической генерации методов, конструкторов и других элементов Java-кода.
- JSON Web Token (JWT):
  jjwt: Библиотека для работы с JSON Web Tokens.

