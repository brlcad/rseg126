docker build -t hellogcc:latest -f ./Dockerfile.gcc .
docker build -t hellocentos:latest -f ./Dockerfile.centos .
docker build -t helloalpine:latest -f ./Dockerfile.alpine .

docker run -it --rm hellogcc:latest
docker run -it --rm hellocentos:latest
docker run -it --rm helloalpine:latest
