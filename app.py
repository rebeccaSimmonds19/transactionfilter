import psycopg2
from os import environ


class DatabaseLoader:
    # main function
    def __init__(self):
        self.server = environ.get("SERVER")
        self.user = environ.get("USER")
        self.password = environ.get("PASSWORD")
        self.dbname = environ.get("DBNAME")

    # takes the csv and inserts it into the db
    def setup_db(self):
        conn = psycopg2.connect(host=self.server,
                                port=5432,
                                dbname=self.dbname,
                                user=self.user,
                                password=self.password)

        cur = conn.cursor()

        # does table exist
        tb_exists = "select exists(" \
                    "select relname from pg_class where relname='"\
                    + "clients" + "')"
        cur.execute(tb_exists)
        if cur.fetchone()[0] is False:
            # make table
            cur.execute(
                'create table clients('
                'ID INT, '
                'LIMIT_BAL INT, '
                'SEX INT, '
                'EDUCATION INT, '
                'MARRIAGE INT, '
                'PAY_0 INT, '
                'PAY_2 INT, '
                'PAY_3 INT, '
                'PAY_4 INT, '
                'PAY_5 INT, '
                'PAY_6 INT);')
            conn.commit()
        # copy csv
        f = open(r'UCI_Credit_Card.csv', 'r')
        cur.copy_from(f, "clients", sep=',')
        conn.commit()
        f.close()

if __name__ == '__main__':
    dbl = DatabaseLoader()
    dbl.setup_db()
