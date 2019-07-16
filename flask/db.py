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

def updatePoints(points, nameId, gameId):
    return getData("insert into points (points, id, gameid, gamesplayed) values ("+points+", "+nameId+", "+gameId+", 1) on conflict on constraint points_un do update set points="+points+" where points.id="+nameId+" and points.gameid="+gameId+";")

def addDefPoints(nameId,gameId):
    return getData("insert into points (points, id, gameid, gamesplayed) values (default, "+nameId+", "+gameId+", 0);")

def addPoints(points,nameId,gameId):
   return None

def getPoints(gameId):
    return getData("select points.id, users.name, games.gameid , games.gamename, points.points from users, points, games where users.id = points.id and points.gameid = games.gameid and games.gameid = "+gameId+";")

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