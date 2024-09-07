# PasteBin
Данный проект является упрощённым аналогом сервиса pastebin.com. 
Основные возможности проекта:
1. Пользователь регистрируется (без использования Spring Security) и затем создает "пасту" (блок теста), после чего получает уникальную ссылку на неё. Любой другой пользователь может перейдя по ссылке увидеть эту пасту. Пользователи и пасты хранятся в двух разных таблицах базы данных PostgreSQL. 
2. При создании пасты можно выбрать время, в течение которого паста будет доступна. По истечении этого времени паста будет удалена. 
3. Если паста становится "популярной" (то есть её запрашивали определенное количество раз), она добавляется в кэш (с помощью Redis), таким образом стабилизируя работу приложения.
4. Само Java приложение, PostgreSQL и Redis контейнеризированы в Docker с помощью файла docker-compose.yml, для удобства развертывания. 

Все остальные методы проекта являются CRUD-операциями по добавлению записей в базу, их изменению и удалению. Примеры запросов таких методов описаны ниже:

Регистрация пользователя POST запрос localhost:8081/PasteBin/registration
{
	"userName": "vlad",
	"userEmail": "anton@something.com",
	"userPassword": "123123"
}

Log-in GET запрос localhost:8081/PasteBin/login
{
	"userName": "vlad",
	"userPassword": "123123"
}

Удаления пользователя DELETE запрос localhost:8081/PasteBin/delete-user
{
	"userName": "vlad",
	"userPassword": "123123"
}

Изменение пароля пользователя PUT запрос localhost:8081/PasteBin/update-password
{
	"userPasswordNew": "5656",
	"userPassword": "123123"
}

Создание новой пасты POST запрос localhost:8081/PasteBin/create-pasta
{
	"pastaName": "anekdot",
	"pastaText": "pro chapayeva",
	"status": "private",
	"lifetime": "min_10",
	"userName": "vlad",
	"userPassword": "5656"
}

Удаление пасты DELETE запрос localhost:8081/PasteBin/delete-pasta
{
	"pastaName": "anekdot"
}

Изменение названия пасты PUT запрос localhost:8081/PasteBin/update-pasta-name
{
	"pastaNameNew": "nesmeshnoy anekdot",
	"pastaName": "anekdot"
}

Чтобы получить доступ к пасте просто перейдите по ссылке, которую возвращает запрос на создание пасты.

GET запрос localhost:8081/PasteBin вернёт 10 последних опубликованных паст.

Так же есть метод, который отправляет сообщение со сгенерированным кодом на почту (для завершения регистрации). Для его корректной работы необходимо в файле application.yml и в классе CustomMail (метод getJavaMailSender) прописать логин и пароль (от почты, с которой будет вестись рассылка).
Пример данного GET запроса http://localhost:8081/PasteBin/send-email
{
	"userEmail": "sevlaginmisha@gmail.com"
}
