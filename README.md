# Knoflik
Knoflik - это некоммерческий сервис для игры в аналог популярной викторины "Своя игра", написанная на языке Java SE11, с использованием Spring Framework. 

Суть игры состоит в том, что вам предстоит сыграть несколько тем, по 5 вопросов в каждой совершенно на любую тематику. 
Каждый вопрос имеет свой номинал, который добавляется к вашим очкам, если вы угадываете, и отнимается, если вы дали неверный ответ. 
Игра ведется до тех пор, пока не будут отыграны все темы.

Игра является учебным проектом студентов 5 группы 2 курса факультета прикладной математики и информатики БГУ (3 семестр 2020-2021):
* Войтович Ольги ([@olyavoytovich](https://github.com/olyavoytovich))
* Захарневой Марии ([@MariaZacharneva](https://github.com/MariaZacharneva))
* Кацубы Станислава ([@PGSStas](https://github.com/PGSStas))
* Федени Елизаветы ([@elizabethfeden](https://github.com/elizabethfeden))

## Статус ветки master

[![Build Status](https://travis-ci.com/PGSStas/knoflik.svg?branch=master)](https://travis-ci.com/PGSStas/knoflik)

После каждого нового коммита запускается автоматическая сборка и статический 
анализ кода проекта. Обычно это занимает до трех минут. Если виджет загорается
зелёным, всё в порядке. В противном случае (красный или серый цвет) на
каком-то этапе возникли ошибки. Чтобы увидеть их описания, кликните по виджету.
Чтобы просмотреть результаты сборки и проверки кода в другой ветви разработки,
пройдите по [данной ссылке](https://travis-ci.com/github/PGSStas/knoflik/branches)
 и выберите нужный коммит.

## Процесс игры и вид проекта
В игре есть возможность зарегестрироваться или войти под существующем именем и паролем.
![](/screenshot/1.png)
![](/screenshot/2.png)
![](/screenshot/3.png)

Далее предлагается создать комнату или войти в уже готовую, используя код комнаты. Тот, кто создает комнату, автоматически становится админом.
![](/screenshot/7.png)
![](/screenshot/5.png)

**Админ** может  решать правильно ответил человек на вопрос или нет, а также меняет вопросы.
![](/screenshot/6.png)
![](/screenshot/9.png)

**Участник** может дать ответ, если нажмет на кнопку. У **админа** появится результат, и в зависимости от того, правильный ответ или нет, рейтинг **участника** меняется.
![](/screenshot/8.png)