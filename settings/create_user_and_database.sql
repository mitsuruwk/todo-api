CREATE ROLE todoadmin NOSUPERUSER NOCREATEROLE INHERIT LOGIN ENCRYPTED PASSWORD 'K9T0my8eJG8UXncuaTwDC7BclHpOg9H6';
CREATE DATABASE tododb WITH OWNER = todoAdmin ENCODING = 'UTF8' LC_COLLATE = 'ja_JP.UTF-8' LC_CTYPE = 'ja_JP.UTF-8';
