FROM postgres

ENV TZ=America/Sao_Paulo
ENV PGTZ=America/Sao_Paulo
ENV POSTGRES_USER=${PSQL_SERVICO_USER:?error}
ENV POSTGRES_PASSWORD=${PSQL_SERVICO_PASS:?error}
ENV POSTGRES_DB=${PSQL_DATABASE:?error}

RUN sed -ri "s!^#?(max_prepared_transactions)\s*=.*!\1 = 110!" /usr/share/postgresql/postgresql.conf.sample

#RUN mkdir /postgresql/
#RUN mkdir /postgresql/data/
#RUN mkdir /postgresql/data/medvolltsd000
#RUN mkdir /postgresql/data/medvolltsi000


