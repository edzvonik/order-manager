# Управление заказами

Проект "Управление заказами" представляет собой пример RESTful веб-приложения, созданного с использованием Spring Boot и Spring Data JPA. Приложение предназначено для управления заказами и позициями заказов.

## Содержание

1. [Требования](#требования)
2. [Запуск проекта](#запуск-проекта)
3. [Основные концепции](#основные-концепции)
4. [API и CRUD операции](#api-и-crud-операции)
5. [Построение отчета](#построение-отчета)

## Требования

Прежде чем начать, убедитесь, что на вашем компьютере установлены:
- Maven
- Docker, Docker Compose

## Запуск проекта

1. Клонируйте проект с репозитория GitHub:
   ```sh
   git clone git@github.com:edzvonik/order-manager.git
   ```

2. Перейдите в корневую директорию проекта:
   ```sh
   cd order-manager
   ```
   
3. Выполните сборку проекта при помощи Maven:
   ```sh
   mvn clean package
   ```
   
3. Затем сборку Docker образа с проектом:
   ```sh
   docker build .
   ```

4. Запустите проект с помощью Docker Compose:
   ```sh
   docker compose up
   ```
   
Документация к API будет доступна по адресу: `http://localhost:8080/swagger-ui/index.html`.

## Основные концепции

![db-schema](https://github.com/edzvonik/order-manager/blob/master/db-schema.png)

Проект состоит из трех основных сущностей: Заказы, Позиции заказов и Продукты. 
Заказы содержат информацию о дате заказа, связаны с Позициями. Позиции заказов связаны с Продуктами, 
содержат информацию о количестве Продуктов в Позиции, общую стоимость Позиции. Продукты содержат информацию
об имени и стоимости Продукта. 

## CRUD операции

Проект предоставляет следующие CRUD операции для каждой из основных сущностей:

- **Заказы (Orders)**:
  - Создание нового заказа
  - Получение списка всех заказов
  - Получение заказа по его идентификатору
  - Удаление заказа

- **Позиции заказов (Order Items)**:
  - Получение списка всех позиций заказа для конкретного заказа
  - Получение информации о позиции заказа по её идентификатору
  - Обновление информации о позиции заказа
  - Удаление позиции заказа

## Построение отчета

Для построения отчета о заказах за определенный интервал времени, используйте следующий запрос:

```
GET /api/orders/report?start=2023-10-20&end=2023-10-26
```

Этот запрос вернет список заказов с полной информацией, 
включая продукты, оформленные в интервале с 20 октября 2023 года по 26 октября 2023 года.
