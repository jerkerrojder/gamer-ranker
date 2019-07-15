import psycopg2

def getData(req):
    # Connect to an existing database
    conn = psycopg2.connect("dbname=template1 user=hampus")
    
    # Open a cursor to perform database operations
    cur = conn.cursor()
    
    cur.execute(req)
    
    v = None

    print(cur.description)
    if(cur.description != None):
        v = cur.fetchall()

    #make changes persistant with other
    conn.commit()

    cur.close()
    conn.close()
    return v

def addPoints(points,nameId,gameId):
   return None

def getPlayers():
    return getData("select * from users, points, games where users.id = points.id and games.gameid = points.gameid;")

def addPlayer(name):
    return getData("insert into users (name) values ('"+name+"');")

def getGames():
    return getData("select * from games;")

def postGame(name):
    print("POSTGAME")
    return getData("insert into games (gamename) values ('"+name+"');")



def pr(i):
    print(i)
