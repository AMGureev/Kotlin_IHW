# Домашнее задание по КПО №1 "Консольное приложение для сотрудников кинотеатра"
## О работе приложения
Данное приложение реализует основной функционал(на 8 баллов), а также дополнительный (+ 2 балла) функционал. Программа была реализована по принципам ООП и SOLID. 
Данные о пользователях, информация о фильмах, сеансах и купленных билетах находятся в соответсвующих папках ``accounts``, ``movies``, ``sessions`` и ``tickets`` в файлах формата ``json``. Данный формат выбран ввиду 
удобства для чтения обычным пользователем.
## Туториал взаимодейтсвия с пользователем
### Регистрация и авторизация пользователя
Для начала пользователю нужно пройти регистрацию. Для регистрации необходимо ввести в консоль цифру `2`. <br />
Если аккаунт был создан ранее (вы пользуетесь приложением не первый раз), тогда введите в консоль цифру `1`.<br />
Во время входа или регистрации у пользователя спросят `login` и `password`. В случае регистрации логин и пароль необходимо придумать (`login` обязан быть уникальным), а в случае авторизации ввести корректные логин и пароль.<br />
Если надобность в использовании приложения пропала, нужно ввести цифру `3` - в таком случае программа попрощается с пользователем и завершит работу.
```
Welcome to the app!
Choose one of the two actions:
1. Sign in
2. Register
3. Exit
>>
```
Если во время регистрации пользователь ввел занятый `login`, то аккаунт не будет создан и пользователь получит об этом сообщение.<br />
Если во время авторизации пользователь ввел некорректные `login` или `password`, то пользователь получит соответствующее уведомление.
### Работа в основной программе
```
You are on the main page
Choose one action:
#1 Buy ticket
#2 Return ticket
#3 Activate ticket
#4 Display session information
#5 Create session
#6 Create movie
#7 Edit to movie
#8 Edit to session
#9 Display all sessions
#10 Save and exit the program
>>
```
Далее пользователю будет предоставлен перечень возможных 'действий', которые представлены выше.<br />
#### 1. Покупка билетов
Покупка билетов осуществляется при выборе пользователем цифры `1`. Далее пользователю необходимо выбрать сеанс, на который он хочет попасть, а также номер места в зале. <br />
Пример:
```
>>1
You want to buy a ticket!
Enter the session ID:
>>1
Enter seat's number:
>>23
Error! - the session does not exist
```
В случае возникновения каких-то проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ввод несуществующей сессии', 'Попытка покупки ранее приобретенного билета', 
'Некорректный ввод места' и другие.
#### 2. Возврат билета
Возврат билета осуществляется при выборе пользователем цифры `2`. Далее пользователю необходимо написать номер сеанса и номер места в зале, на которые был приобретен билет.<br />
Пример:
```
>>2
You want to return a ticket!
Enter the session ID:
>>12
Enter seat's number:
>>6
Error! - the session does not exist
```
Вернуть можно только ранее купленный билет. Также нельзя вернуть ранее активированный билет. <br />
В случае возникновения каких-то проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ввод несуществующей сессии', 'Попытка возврата некупленного билета', 
'Некорректный ввод места' и другие.
#### 3. Активация билета 
Активация билета осуществляется при выборе пользователем цифры `3`. Далее пользователю необходимо написать номер сеанса и номер места в зале, на которые был приобретен билет.<br />
Пример:
```
>>3
You want to activate a ticket!
Enter the session ID:
>>1
Enter seat's number:
>>2
Error! - the session does not exist
```
Активировать можно только приобретенные билеты, которые не были активированы ранее.<br />
В случае возникновения каких-то проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ввод несуществующей сессии', 'Попытка активации некупленного билета', 
'Попытка активации использонного билета' и другие.
#### 4. Посмотреть информацию о сессии(сеансе)
Просмотр доступных и приобретенных билетов нужной сессии осуществляется при выборе пользователем цифры `4`.<br />
Пример:
```
>>4
You want to see information about the session!
Enter the session ID:
>>0
Choose which ticket information you want to see:
#1 Purchased
#2 Available
>>1
All tickets are available for purchase!
```
Просмотр информации доступен только для существующих сеансов. Пользователя спросят, какие билеты его интересуют - купленные или доступные. В зависимости от ввода 
пользователя приложение выведет необзодимую информацию. Если купленных билетов нет - пользователь увидит соответствующее сообщение. Если доступных билетов нет (все билеты были
куплены) - пользователь также увидит соответствующее сообщение.
В случае возникновения каких-то проблем пользователь получит соответствующее сообщение в консоли. Пример ошибки: 'Ввод несуществующей сессии'.
#### 5. Создание сессии
Создание сессии осуществляется при выборе пользователем цифры `5`.<br />
Пример:
```
>>5
You want to create a session!
Enter the movie title:
>>Minecraft 1.8
Enter the session start time (format- dd/MM/yy:HH:mm:ss):
>>11/11/11:12:55:00
The session has been created!
```
Создание сессии происходит только с существующим фильмом, а также при отсутствии временных накладок с другими сессиями. В случае возникновения каких-то 
проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ошибка - сессия накладывается на другую', 'Такого фильма нет'.
#### 6. Создание фильма
Создание фильма осуществляется при выборе пользователем цифры `6`.<br />
Пример:
```
>>6
You want to create a movie!
Enter the movie title:
>>Minecraft 1.8
Enter the movie duration (in minutes):
>>120
The movie has been created!
```
Создание фильма происходит только с оригинальным названием, а также корректными значениями параметра 'продолжительность'. В случае возникновения каких-то 
проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ошибка - название не оригинальное', 'Такого фильма нет'.
#### 7. Редактирование фильма
Редактирование информации о фильме осуществляется при выборе пользователем цифры `7`.<br />
Пример:
```
>>7
You want to edit a movie!
Enter the movie title:
>>Minecraft 1.8
Enter the new movie title (if no change is needed, leave it blank):
>>
Enter the new movie's duration (if no change is needed, leave it '0'):
>>123
Movie information has been successfully updated!
```
Редактирование фильма возможно только если тот существует, новое имя не повторяет существующие названия, а также при корректном значении параметра 'продолжительность'. 
В случае возникновения каких-то 
проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ошибка - название не оригинальное', 'Продолджительность фильма не может быть отрицательной'.
#### 8. Изменить сессию
Редактирование информации о сессии осуществляется при выборе пользователем цифры `8`.<br />
Пример:
```
>>8
You want to edit a session!
Enter the session ID
>>0
Enter tne new session's start data (format - dd/MM/yy:HH:mm:ss):
>>15/05/23:12:12:12
Session information has been successfully updated!
```
Редактирование сессии возможно только если сессия существует, а также при отсутствии временных накладок с другими сессиями. В случае возникновения каких-то 
проблем пользователь получит соответствующее сообщение в консоли. Примеры ошибок: 'Ошибка - сессия накладывается на другую', 'Такой сессии нет'.
#### 9. Посмотреть информацию о всех сессиях
Просмотр информации о сессиях Просмотр информации о сессиях осуществляется при выборе пользователем цифры `9`.<br />
Пример:
```
>>9
You want to see all available sessions!
Session ID: 0; movie: Minecraft 1.8; time start: 2023-05-15T12:12:12
```
Пользователь увидит информацию обо всех имеющихся сессиях. Если сессий нет - пользователь получит соответствующее сообщение.
#### 10. Выход из приложения 
Сохранение данных и завершение работы приложения осуществляется при выборе пользователем числа `10`.<br />
```
>>10
You have completed the program!
Goodbye!
```
В этот момент приложение сохраняет все изменения в соответствующие ``json`` файлы и завершает работу.
## Содержание работы
В папке ``data`` в репозитории хранится 2 файлика с выполненными ``UML`` диаграммами.
Работу выполнил Гуреев Александр Михайлович, БПИ227.

