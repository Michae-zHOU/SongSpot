cd ./client
docker build -t wecanwalk/songspot-client .
cd ..

cd ./server
docker build -f DockerfileWin -t wecanwalk/songspot-server .
cd ..
