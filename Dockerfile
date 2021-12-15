FROM ubuntu
MAINTAINER csm@brandeis.edu

# set up java
RUN apt-get update && apt-get install -y ca-certificates-java && update-ca-certificates -f && apt-get clean
RUN apt-get update && apt-get install -y default-jdk && apt-get install -y ant && apt-get clean

# set up sshd
RUN apt-get install -y openssh-server && apt-get clean
RUN useradd -rm -d /home/user -s /bin/bash -g root -G sudo -u 1234 user
RUN echo 'password\npassword\n' | passwd -q user

# clone SieveOfEratosthenes (as backup)
RUN apt-get update && apt-get install -y git
RUN cd ~user && git clone https://github.com/brlcad/rseg126.git && chown -R user rseg126

# otherwise, ideally set up to use a mapped checkout
RUN echo "if test -f /code/build.xml; then cd /code; elif test -f ~user/rseg126/SieveOfEratosthenes/build.xml; then cd ~user/rseg126/SieveOfEratosthenes; fi" >> ~user/.bash_profile

# announce how to run on login, using mapped or cloned
RUN test -f /code/build.xml && cd /code || cd ~user/rseg126/SieveOfEratosthenes; \
    echo "test -f build.xml && echo \"===============================================\"" >> ~user/.bash_profile && \
    echo "test -f build.xml && echo \"Type 'ant run' to run the Sieve of Eratosthenes...\"" >> ~user/.bash_profile && \
    echo "test -f build.xml && echo \"===============================================\"" >> ~user/.bash_profile

# for root debugging
# RUN sed -i 's/.*PermitRootLogin .*/PermitRootLogin yes/' /etc/ssh/sshd_config

EXPOSE 22

ENTRYPOINT service ssh start && bash
