var games = null;
var currentGameId = 1;
const ur = "http://10.46.1.101:8080/"

//MOCK OBJECTS
resp2 = { games: ["Mario Cart Galaxy 2",
"Fifa 69",
"Foosball"]};

resp = { games: [
{id: "1", name: "Mario Cart Galaxy 2"},
{id: "2", name:"Fifa 69"},
{id: "3", name:"Foosball"}]};


rankings = {
rankings: 
[{gameId: "-1", playerId: "1", name: "Adam", rank: 1500},
{gameId: "-1", playerId: "2", name: "Steph", rank: 1400},
{gameId: "-1", playerId: "3", name: "Hampus", rank: 1300}]
};


//On Document load load all games
document.addEventListener('DOMContentLoaded', function() {
    const URL = ur+"game";

    $.get(URL, (data, status) => {
        changeDrop(data);
        games = data;
        //console.log("Games: " + games[0].gameName);
    })
}, false);

function getGameId(name){
    console.log("TRYING TO GET ID FROM NAME");
    var i;
    for(i = 0; i < games.length; i++){
        if(games[i].gameName == name){
            return games[i].id;
        }
    }
}

function getNameFromId(id){
    console.log("TRYING TO GET NAME FROM ID");
    for(i = 0; i < games.length; i++){
        if(games.id == id){
            return games[i].gameName;
        }
    }
}

//FETCHES THE NAMES FOR PLAYES OF A CERTAIN GAME FROM DB
function fetchNames(game){
    console.log("FetchNames game name: "+game);
    $("#names").empty();
    currentGameId = getGameId(game);
    //THIS IS WHERE WE NEED TO QUERY DATABASE FOR NAMES FOR SAID GAME
    console.log("GETTING THE ID: " + getGameId(game) + ". Right now its hardcoded as one so if ID: was 1 then good, Also  Current GameId is: " + currentGameId);
    const URL = ur+"points";
    var params = {
        gameid: 1 // ****************** HARD CODED ************************
    }

    $.get(URL, params, (data, status) => {
        console.log("THIS IS THE PLAYERS" + data + "status: " + status);
        var j = 1;
        data.forEach(e => {
            console.log(e);
            dispNames(e,j);
            j = j +1;
        })
    })
    document.getElementById("mySidenav").style.width = "0";
    $("#gameTitle").html(game);
    
}


//diplays the names, person should contain all the info about said person in correct format 
function dispNames(person,i){
    //gets username from name id
    var URL = ur+"getusername";
    var params = {
        userid: person.userId
    }
    var username;
    $.ajax({
        async: false,
        type: 'GET',
        url: URL,
        data: params,
        success: function(data) {
            console.log(data);
            username = data;
        }
    });
    //end of getting name from nameid
    var ul = document.querySelector("#names");
    var node = document.createElement("LI");
    node.setAttribute("class", "list-group-item row ");

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-3");
    var textNode = document.createTextNode(i);
    pNode.appendChild(textNode);
    node.appendChild(pNode);

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-5");
    var nameNode = document.createTextNode(username);

    pNode.appendChild(nameNode);
    node.appendChild(pNode);

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-4");
    var textNode = document.createTextNode(Math.floor(person.points));
    pNode.appendChild(textNode);
    node.appendChild(pNode);

    //console.log(person);

    ul.appendChild(node);

    

}

//CHANGES THE GAMES DISPLAYED BY SIMEMENU
function changeDrop(r){
    var ul = document.querySelector("#games");
    r.forEach(elem => {
        //this needs to be a loop making LI elements
        var node = document.createElement("LI");
        var aNode = document.createElement("A");
        aNode.setAttribute("href", "#"+elem.gameName);
        aNode.setAttribute("onclick","fetchNames(\"" + elem.gameName + "\")");
        var textNode = document.createTextNode(elem.gameName);
        aNode.appendChild(textNode);
        node.appendChild(aNode);
        ul.appendChild(node);
    });

    
    
}

//HERE IS WHERE WE GON SEND THE STUFF
//looks for the name and sends it
function addPlayer(){
    var form = document.querySelector("#nameInput").value;
    //console.log(form);
    var url = ur+"user";
    var params = {
        gameId: 1, // ****************** HARD CODED ************************
        username: form
    }
    $.post(url,params, (data, status) => {
        console.log("Status: " + status + " Data: " + data);
    })

}

function addMatch(){
    var winner = document.querySelector("#winnerInput").value;
    var loser = document.querySelector("#loserInput").value;
    console.log("Winner: " + winner);
    console.log("loser: " + loser);

    var url = ur+"match";

    var params = {
        gameid: 1,
        user1name: winner,
        user2name: loser,
        scoreuser1: 1,
        scoreuser2: -1
    }
    $.post(url,params, (data, status) => {
        console.log("Status: " + status + " Data: " + data);
        
    })

}
 