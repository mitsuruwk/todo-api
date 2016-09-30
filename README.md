## How to use

userとdatabaseの作成
```
% psql -f settings/create_user_and_database.sql
```

```
gradle flywayBaseline
gradle flywayMigrate
```

* gradle
https://gradle.org/
ビルド自動化ツール

* flyway
https://flywaydb.org/

* swagger
http://swagger.io/
REST API console

* Jersey
https://jersey.java.net/documentation/latest/index.html
