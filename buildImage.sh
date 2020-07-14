cd ./client
docker build -t wecanwalk/songspot-client .
cd ..

cd ./server
DOCKER_BUILDKIT=1 docker build -t wecanwalk/songspot-server .
cd ..
