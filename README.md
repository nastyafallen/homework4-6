# Hogwarts School Application

**Hogwarts School Application** - это веб-приложение построено с использованием Spring Boot и Java, предназначено для управления информацией о факультетах и студентах, хранения аватаров и выполнения различных запросов. 
Для хранения данных используется база данных PostgreSQL, а для управления версиями базы данных — Liquibase.

## Обзор

Это приложение является бэкенд-решением для управления данными, связанными с факультетами и студентами Хогвартса. 
Пользователи могут добавлять, обновлять и удалять записи, а также получать специфическую информацию через полный REST API.


## Используемые технологии

**Spring Boot**: Фреймворк для разработки веб-приложений.
**Java**: Язык программирования.
**PostgreSQL**: Система управления базами данных для хранения и управления данными приложения.
**Liquibase**: Инструмент для контроля версий базы данных и миграций.


## Функции

- **FacultyController** (/faculty)
  Добавление нового факультета: POST /
  Поиск факультета по ID: GET /find?id={id}
  Изменение данных факультета: PUT /
  Удаление факультета по ID: DELETE /{id}
  Поиск факультетов по цвету: GET /{color}
  Поиск факультетов по имени или цвету (игнорируя регистр): GET /?value={value}
  Получение списка студентов факультета по ID: GET /{id}/students
  Поиск самого длинного названия среди факультетов: GET /longestFacultyName

- **StudentController** (/student)
  Добавление нового студента: POST /
  Поиск студента по ID: GET /find?id={id}
  Обновление данных студента: PUT /
  Удаление студента по ID: DELETE /{id}
  Получение списка студентов заданного возраста: GET /filter?age={age}
  Получение списка студентов в возрастном интервале: GET /?age1={age1}&age2={age2}
  Получение факультета студента по ID: GET /{id}/faculty
  Подсчет общего количества студентов: GET /count
  Вычисление среднего возраста студентов: GET /averageAge
  Получение списка последних добавленных студентов (по количеству number): GET /lastStudents?number={number}
  Получение списка студентов, чьи имена начинаются с указанной буквы, в алфавитном порядке: GET /alphabeticalOrder?letter={letter}
  Получение списка всех имен студентов и вывод их в консоль: GET /listOfStudents

- **AvatarController** (/avatar)
  Сохранение нового аватара для студента по ID в базе данных и в локальной папке: POST /{studentId}
  Получение аватара из базы данных в виде байтового массива: GET /{id}/avatar-from-db
  Получение пути к файлу аватара в локальной папке: GET /{id}/avatar-from-file
  Получение списка всех аватаров из базы данных с пагинацией: GET /allAvatars

- **InfoController** (/info)
  Отображение порта, на котором работает приложение: GET /getPort