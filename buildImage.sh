cd ./frontend
docker build -t wecanwalk/songspot-client .
cd ..

cd ./backend
DOCKER_BUILDKIT=1 docker build -t wecanwalk/songspot-server .
cd ..
