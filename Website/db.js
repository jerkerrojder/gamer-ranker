var games = null;
var currentGameId = 1;
var currentGame = null;
var allUsers = null;

const ur = "http://10.46.0.147:5000/"


//On Document load load all games
document.addEventListener('DOMContentLoaded', function() {
    const URL = ur+"game";

    $.get(URL, (data, status) => {
        console.log(data);
        changeDrop(data);
        games = data;
        //fetchNames(data[0].gameName);
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
    console.log("TRYING TO GET NAME FROM ID:" + games.length);
    for(i = 0; i <= games.length; i++){
        if(games[i].id == id){
            console.log(games[i].gameName);
            return games[i].gameName;
        }
    }
}

//FETCHES THE NAMES FOR PLAYES OF A CERTAIN GAME FROM DB
function fetchNames(game){
    $("#names").empty();
    console.log(game[0]);
    
    const URL = ur+"points";
    var params = {
        gameId: game[0]
    }

    $.post(URL, params, (data, status) => {
        var j = 1;
        console.log("DATA FROM POINTS:");
        console.log(data);
        data.sort(function (a, b) {
            return b[4] - a[4];
          });
            data.forEach(e => {
            dispNames(e,j);
            j = j +1;
        })
    })
    document.getElementById("mySidenav").style.width = "0";
    $("#gameTitle").html(game[1]);

    console.log(document.querySelector("#names"));
    $("#modalTitle").html(game[1]);
}


//diplays the names, person should contain all the info about said person in correct format 
function dispNames(person,i){

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
    var nameNode = document.createTextNode(person[1]);

    pNode.appendChild(nameNode);
    node.appendChild(pNode);

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-4");
    var textNode = document.createTextNode(Math.floor(person[4]));
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
        aNode.setAttribute("href", "#"+elem[1]);
        aNode.setAttribute("onclick","fetchNames([" + elem[0] + ",\"" + elem[1] + "\"])");
        var textNode = document.createTextNode(elem[1]);
        aNode.appendChild(textNode);
        node.appendChild(aNode);
        ul.appendChild(node);
    });

    
    
}



//HERE IS WHERE WE GON SEND THE STUFF
//looks for the name and sends it
function addPlayer(){
    var form = document.querySelector("#nameInput").value;
    console.log(form);
    var url = ur+"addplayer";
    var params = {
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
    console.log("Adding match id:" + currentGameId);
    var params = {
        gameid: currentGameId, //****************************HARSCODED */
        user1name: winner,
        user2name: loser,
        scoreuser1: 1,
        scoreuser2: -1
    }
    $.post(url,params, (data, status) => {
        console.log("Status: " + status + " Data: " + data);
        
    })
    console.log(currentGameId);
    fetchNames(getNameFromId(currentGameId));

}

function getAllUsers(){
    //DET SKA ANVändas datalists för att autocompleata.
    var URL = ur+"players"
    $.get(URL, (data, status) => {
        console.log(data);
    })
}
 