***
***Сервис, который обращается к сервису курсов валют и отображает gif***
***
#### Инструкция по запуску
#### 1) Клонировать репозиторий
    https://github.com/pavelmikhailovsky/currencyRate.git
#### 2) Создать образ докера
    docker build -t your-name .
#### 3) Запустить контейнер
    docker run -p 8080:8080 your-name
#### 4) Перейти в браузер и сделать запрос
    localhost:8080/{currencyCode}
