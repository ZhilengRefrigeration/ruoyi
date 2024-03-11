#!/bin/bash

# Define the image name as a variable for easy modification
IMAGE_NAME="ryas-auth"

# Prompt the user to enter a version number (tag) for the image
echo "Please enter the image version number (tag):"
read TAG

# Check if the version number has been entered
if [[ -z "$TAG" ]]; then
    echo "The image tag cannot be empty !"
    exit 1
fi

DOCKERFILE_DIR='.'

docker buildx build -t $IMAGE_NAME:$TAG $DOCKERFILE_DIR

echo
echo "================================================"
echo "The details of the built image are as follows:"
echo
docker images | grep "$IMAGE_NAME.*$TAG"

