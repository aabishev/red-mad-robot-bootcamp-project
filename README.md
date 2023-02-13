# red-mad-robot-bootcamp-project

JAR необходимо запустить через командную строку, с помощью команды java -jar C:\Users\user\red-mad-robot-bootcamp-project-0.0.1-SNAPSHOT.jar

Документация API:

РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ: Данный метод вернет токен для отправки запросов и email пользователя.
Метод: POST. URL: localhost:8080/api/v1/auth/register
{
"email":"bookingcerikovic@gmail.com",
"password":"bookingcerikovic@gmail.com"
}

АУТЕНТИФИКАЦИЯ ПОЛЬЗОВАТЕЛЯ: Данный метод вернет токен для отправки запросов и email пользователя.
Метод: POST. URL: localhost:8080/api/v1/auth/authenticate
{
"email":"bookingcerikovic@gmail.com",
"password":"bookingcerikovic@gmail.com"
}

ДЛЯ СЛЕДУЮЩИХ API НЕОБХОДИМА АВТОРИЗАЦИЯ ТОКЕНОМ(Bearer Token)!

ПОЛУЧЕНИЕ СПИСКА ОБЪЯВЛЕНИИ ПО ФИЛЬТРАЦИИ: Данный метод вернет список всех активных объявлении с фильтрами. Фильтр указывается в параметрах запроса.
Есть 4 параметра фильтрации filterParam: byNameDesc(список по названию в алфавитном порядке), byNameAsc(список по названию в обратном алфавитном порядке),
byMinPriceDesc(список по минимальной цене по убыванию), byMinPriceAsc(список по минимальной цене по возрастанию)
Метод: GET. URL: localhost:8080/api/v1/ad?filterParam=byMinPriceAsc

