import sqlite3
from src.config import DATABASE_PATH


def get_connection():

    connection = sqlite3.connect(DATABASE_PATH)

    connection.execute("PRAGMA foreign_keys = ON")

    return connection
