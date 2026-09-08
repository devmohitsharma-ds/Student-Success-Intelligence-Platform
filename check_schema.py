from src.database import get_connection

connection = get_connection()
cursor = connection.cursor()

for table in ["students", "academics", "attendance", "lifestyle"]:

    print("\n==============================")
    print(table.upper())
    print("==============================")

    result = cursor.execute(
        "SELECT sql FROM sqlite_master WHERE type='table' AND name=?",
        (table,)
    ).fetchone()

    print(result[0] if result else "TABLE NOT FOUND")

connection.close()
