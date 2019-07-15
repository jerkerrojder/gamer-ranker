from flask import request
import db
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

app.run(host= '0.0.0.0') 
