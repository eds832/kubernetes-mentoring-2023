docker-compose up -d users-db
docker-compose up -d posts-db
TIMEOUT /T 30
start "" "%PROGRAMFILES%\Git\bin\sh.exe" --login docker-compose exec users-db psql -U admin -c 'CREATE DATABASE users'
docker-compose up -d user-app
docker-compose up -d post-app