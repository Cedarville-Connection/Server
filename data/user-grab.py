"""
Downloads JSON data from RandomUser.me and extracts the info we want, storing it locally for exporting to a MySQL instance
"""

import argparse
import json
import logging
import requests

import mysql.connector

API_URL = "https://randomuser.me/api"
RESULT_KEPT_FIELDS = ["dob", "email", "gender", "location", "name", "nat", "phone", "picture", "registered"]


def export_to_mysql(db, table, username, password, values):
    logging.info(f"Exporting results to MySQL: {db}:{table}")

    # Pre-declare connection and cursor for cleanup
    conn = None
    cur = None
    try:
        logging.info("Connecting to local MySQL instance")
        conn = mysql.connector.connect(host="localhost", user=username, passwd=password, db=db)

        cur = conn.cursor()

        logging.info(f"Inserting users into {table}")
        cur.executemany(f"""insert into {table} ({", ".join(values.keys())}) values (%s, %s....)""", values)

        logging.info("Closing connection to DB")
        conn.close()
    except mysql.connector.Error as e:
        logging.error(f"MySQL error occurred: {e}")
        if cur is not None:
            cur.close()
        if conn is not None:
            conn.close()

    logging.info("MySQL interaction complete")

def main(args):
    # Retrieve data from RandomUser.me
    params = {"results": args.number}
    result = None
    if args.seed:
        logging.info(f"Requesting {args.number} users with seed {args.seed}...")
        if not args.dry:
            result = requests.get(API_URL, params={"seed": args.seed, **params})
    else:
        logging.info(f"Requesting {args.number} users with random seed")
        if not args.dry:
            result = requests.get(API_URL, params=params)

    if result is None:
        logging.info("Dry run complete, no data retrieved")
        return 0

    if result.status_code != requests.codes.ok:
        logging.error("Failed to complete request")
        return 1
    
    # Extract only the fields we want
    extracted = []
    logging.info(f"Received response from API with {result.json()['info']['results']} results")
    logging.info(f"Extracting {len(RESULT_KEPT_FIELDS)} fields from each user: {', '.join(RESULT_KEPT_FIELDS)}")
    for user in result.json()["results"]:
        extracted.append({k: val for k, val in user.items() if k in RESULT_KEPT_FIELDS})
        assert len(extracted[-1].keys()) == len(RESULT_KEPT_FIELDS)

    logging.info(f"Succesfully extracted {len(extracted)} results")

    if args.mysql:
        export_to_mysql(args.db, args.table, args.user, args.password)

    logging.info("Dumping local copy of JSON to dump.json")
    with open("dump.json", 'w') as f:
        f.write(json.dumps(extracted))

    logging.info("Extraction complete")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="randomuser.me extractor")
    parser.add_argument("--number", '-n', type=int, default=5, help="Number of users to retrieve (default 5, maximum of 5000)")
    parser.add_argument("--seed", type=str, help="Seed for API to generate same results each time")
    parser.add_argument("--dry", action="store_true", default=False, help="Run without making request to user generator")

    sql_group = parser.add_argument_group("MySQL", "Arguments related to MySQL exporting. Passing --mysql requires all of these as well")
    sql_group.add_argument("--mysql", action="store_true", default=False, help="Indicate the extracted data should be stored in a MySQL instance")
    sql_group.add_argument("--user", '-u', help="MySQL user to login as for export")
    sql_group.add_argument("--pass", '-p', dest="password", help="MySQL password to login with")
    sql_group.add_argument("--db", help="MySQL Database to store data in")
    sql_group.add_argument("--table", '-t', help="MySQL table to store data in")

    # Do parsing
    args = parser.parse_args()

    # Configure logging
    logging.basicConfig(format="[%(levelname)s] %(message)s", level=logging.INFO)

    if args.mysql and (args.user is None or args.password is None or args.db is None or args.table is None):
        parser.error("--mysql requires --user, --pass, --db, and --table as well")

    exit(main(args))
