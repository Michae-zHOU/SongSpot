#!/bin/sh
DOCKERHUB_NAME="wecanwalk"
echo $DOCKERHUB_NAME

if [ "$#" -ne 1 ]; then
    echo "Miss image version"
    exit 1
fi

cd ..

cd ./client
docker build -t $DOCKERHUB_NAME/songspot-client:$1 .
cd ..

cd ./server
DOCKER_BUILDKIT=1 docker build -t $DOCKERHUB_NAME/songspot-server:$1 .
cd ../deployment

docker push $DOCKERHUB_NAME/songspot-client:$1
docker push $DOCKERHUB_NAME/songspot-server:$1
