from flask import request
import db, elo
import json
from flask import Flask
from flask import jsonify
app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World!"

@app.route("/game")
def getGames():
    r = db.getGames()
    print(r)
    return jsonify(r)

@app.route("/points", methods=['POST'])
def getPoints():
    print(request.form['gameId'])
    r = db.getPoints(request.form['gameId'])
    print(r)
    return jsonify(r)

@app.route("/players")
def getPlayers():
    r = db.getPlayers()
    print(r)
    return jsonify(r)

@app.route("/gametest")
def getGamesTest():
    games =[
                    {"id": "1", "gameName": "Mario Cart Galaxy 2"},
                    {"id": "2", "gameName":"Fifa 69"},
                    {"id": "3", "gameName":"Foosball"}
            ] 
    return jsonify(games)


@app.route("/addplayer", methods=['POST'])
def addPlayer():
    #print(request.form['username'])
    r = db.addPlayer(request.form['username'])
    print(r)
    return jsonify(r)

@app.route("/match", methods=['POST'])
def addMatch():
    req = request.form
    print("winner: " + req['winner'])
    print("loser: " + req['loser'])
    print("gameid: " + req['gameid'])
    elo.eval_match(req['winner'], req['loser'], req['gameid'])

app.run(host= '0.0.0.0') 
