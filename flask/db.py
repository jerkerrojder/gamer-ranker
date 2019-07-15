import psycopg2

def getData(req):
    # Connect to an existing database
    conn = psycopg2.connect("dbname=template1 user=hampus")
    
    # Open a cursor to perform database operations
    cur = conn.cursor()
    
    cur.execute(req)

    v = cur.fetchall()
    
    cur.close()
    conn.close()
    return v

def getPlayers():
    return getData("select * from users, points, games where users.id = points.id and games.gameid = points.gameid;")

def getGames():
    return getData("select * from games;")

def postGame(name):
    print("POSTGAME")
    return getData("insert into games (gamename) values ('"+name+"');")

def pr(i):
    print(i)
