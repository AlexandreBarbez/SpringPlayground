# SpringPlayground
Spring playground


## Docker config


### pg local super sécurisé:

```
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: postgres_local
    restart: always
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: mydatabase
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin_local
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: alexandre.barbez@gmail.com
      PGADMIN_DEFAULT_PASSWORD: password
    ports:
      - "5050:80"
    depends_on:
      - postgres

volumes:
  postgres_data:
    driver: local

```


## test de charge

utiliser wrk pour spammer 

### GET 
```
wrk -t10 -c1000 -d30s http://localhost:8080/utilisateurs/2
```

### POST
```
wrk -t5 -c500 -d20s -s post.lua http://localhost:8080/utilisateurs
```
necessite un fichier post.lua (script Lua)
```
wrk.method = "POST"
wrk.body   = '{"nom": "Jean", "email": "jean@example.com"}'
wrk.headers["Content-Type"] = "application/json"
```
