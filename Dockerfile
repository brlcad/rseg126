FROM ubuntu
MAINTAINER csm@brandeis.edu

# set up java
RUN apt-get update && apt-get install -y ca-certificates-java && update-ca-certificates -f && apt-get clean
RUN apt-get update && apt-get install -y default-jdk && apt-get install -y ant && apt-get clean

# set up sshd
RUN apt-get install -y openssh-server && apt-get clean
RUN useradd -rm -d /home/user -s /bin/bash -g root -G sudo -u 1234 user
RUN echo 'password\npassword\n' | passwd -q user

# for root debugging
# RUN sed -i 's/.*PermitRootLogin .*/PermitRootLogin yes/' /etc/ssh/sshd_config

ENTRYPOINT service ssh start && bash
