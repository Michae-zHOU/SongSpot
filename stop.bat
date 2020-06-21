IF [%1]==[] goto regular

IF %1%==dev goto dev

:regular
docker-compose -f ./docker-compose-win.yml down
goto done

:dev
docker-compose -f ./docker-compose-backend-dev.yml down
goto done

:done
echo Done