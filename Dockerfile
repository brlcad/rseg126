FROM ubuntu
MAINTAINER csm@brandeis.edu
RUN apt-get update && apt-get install -y openssh-server
RUN sed -i 's/.*PermitRootLogin .*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN echo 'password\npassword\n' | passwd -q root
ENTRYPOINT service ssh start && bash
