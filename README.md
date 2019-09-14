### Uroboros
Pet-project 

Основные применяемые технологии:
- Spring MVC, Security
- Freemarker + Bootstrap
- Hibernate + MySQL + Ehcache
- Maven

Для запуска необходимо заполнить поля в файлах 
[application-sample.properties](https://github.com/TaskStorage/uroboros/blob/master/src/main/resources/application-sample.properties), 
[database-sample.properties](https://github.com/TaskStorage/uroboros/blob/master/src/main/resources/database-sample.properties), 
[mail-sample.properties](https://github.com/TaskStorage/uroboros/blob/master/src/main/resources/mail-sample.properties) и убрать приставку "-sample" в названии

Основные возможности:

Пользователи:
- Регистарция с подтверждением через почту и шифрованием паролей
- Разграничение доступа на основе ролей
- Управление пользователями и их ролями

Заметки:
- Создание, редактирование, удаление заметок
- Управление чужими заметками при наличии прав

Прочее:

- Кеширование запросов к базе Ehcache
- Кастомные старницы 403 и 404 ошибок
- Загрузка файлов
- Remember-me
- Валидация всех полей на стороне сервера

Все тоже самое но на Spring Boot [тут](https://github.com/TaskStorage/wireframe)