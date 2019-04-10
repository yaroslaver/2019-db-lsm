# 2019-db-lsm
Курсовой проект 2019 года [курса](https://polis.mail.ru/curriculum/program/discipline/790/) "Использование баз данных" в [Технополис](https://polis.mail.ru).

## Этап 1. In-memory (deadline 2019-04-17)
### Fork
[Форкните проект](https://help.github.com/articles/fork-a-repo/), склонируйте и добавьте `upstream`:
```
$ git clone git@github.com:<username>/2019-db-lsm.git
Cloning into '2019-db-lsm'...
...
$ cd 2019-db-lsm
$ git remote add upstream git@github.com:polis-mail-ru/2019-db-lsm.git
$ git fetch upstream
From github.com:polis-mail-ru/2019-db-lsm
 * [new branch]      master     -> upstream/master
```

### Make
Так можно запустить интерактивную консоль:
```
$ gradle run
```

А вот так -- тесты:
```
$ gradle test
```

### Develop
Откройте в IDE -- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) нам будет достаточно.

В своём Java package `ru.mail.polis.<username>` реализуйте интерфейс [`DAO`](src/main/java/ru/mail/polis/DAO.java), используя одну из реализаций `java.util.SortedMap`. 

Возвращайте свою реализацию интерфейса в [`DAOFactory`](src/main/java/ru/mail/polis/DAOFactory.java#L48).

Продолжайте запускать тесты и исправлять ошибки, не забывая [подтягивать новые тесты и фиксы из `upstream`](https://help.github.com/articles/syncing-a-fork/). Если заметите ошибку в `upstream`, заводите баг и присылайте pull request ;)

### Report
Когда всё будет готово, присылайте pull request в `master` со своей реализацией на review. Не забывайте **отвечать на комментарии в PR** и **исправлять замечания**!
