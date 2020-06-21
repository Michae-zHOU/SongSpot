mkdir d:\docker\volumes\postgres
mkdir d:\docker\volumes\log

IF [%1]==[] goto regular

IF %1%==dev goto dev

:regular
docker-compose -f ./docker-compose-win.yml up -d
goto done

:dev
docker-compose -f ./docker-compose-backend-dev.yml up -d
goto done

:done
echo Done