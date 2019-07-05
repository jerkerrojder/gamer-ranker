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

//currently not used
function req(){
    const Http = new XMLHttpRequest();
    const url=ur+'posts';
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = (e) => {
    console.log(Http.responseText)
    }

}
//this waits for document to load and then we set the dropdowns
document.addEventListener('DOMContentLoaded', function() {
    const URL = ur+"game";
    //console.log(`${URL}`);
    $.get(URL, (data, status) => {
        //console.log(`${data} and status is ${status}`);

        //data.forEach(e => console.log(e.gameName));
        
        changeDrop(data);
        games = data;
        console.log("Games: " + games[0].gameName);
        //console.log(games[0].id);
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
        gameId: 1 // ****************** HARD CODED ************************
    }
    //console.log(`${URL}`);
    $.get(URL, params, (data, status) => {
        console.log("THIS IS THE PLAYERS" + data + "status: " + status);
        var j = 1;
        data.forEach(e => {
            console.log(e);
            dispNames(e,j);
        })
    })
    /*
    var i = 1;
    rankings.rankings.forEach(e => {
        dispNames(e,i);
        i = i + 1; //ät
    });
    */
    document.getElementById("mySidenav").style.width = "0";
    
}


//diplays the names, person should contain all the info about said person in correct format 
function dispNames(person,i){
    
    var ul = document.querySelector("#names");
    /*
    <li class="list-group-item" class="row">
                <p class="col-xs-3">1</p>
                <p class="col-xs-5">Hampus</p>
                <p class="col-xs-4">1500</p>
    </li>
    */
    var node = document.createElement("LI");
    node.setAttribute("class", "list-group-item row ");

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-3");
    var textNode = document.createTextNode(i);
    pNode.appendChild(textNode);
    node.appendChild(pNode);

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-5");
    var textNode = document.createTextNode(person.name);
    pNode.appendChild(textNode);
    node.appendChild(pNode);

    var pNode = document.createElement("P");
    pNode.setAttribute("class", "col-xs-4");
    var textNode = document.createTextNode(person.rank);
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
    /*                       @RequestParam("gameid") Long gameId,
  1                          @RequestParam("user1name") String user1Name,
  2                          @RequestParam("user2name") String user2Name,
  3                          @RequestParam("scoreuser1") Integer scoreUser1,
  4                          @RequestParam("scoreuser2") Integer scoreUser2)
    */

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
 