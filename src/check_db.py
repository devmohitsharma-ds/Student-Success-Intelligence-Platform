import sqlite3

connection = sqlite3.connect("database/ssip.db")
cursor = connection.cursor()

cursor.execute("""
SELECT name
FROM sqlite_master
WHERE type = 'index'
AND name = 'idx_academics_student_id_unique'
""")

result = cursor.fetchone()

print(result)

connection.close()