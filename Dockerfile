FROM centos:latest

ADD requirements.txt .

RUN yum -y install epel-release && \
    yum -y install python-pip && \
    yum clean all

RUN pip install -r requirements.txt

ADD app.py .

ADD UCI_Credit_Card.csv .

CMD ["python", "app.py"]
