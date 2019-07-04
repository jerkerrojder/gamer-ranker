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
[{gameId: "1", playerId: "1", name: "Adam", rank: 1500},
{gameId: "1", playerId: "2", name: "Steph", rank: 1400},
{gameId: "1", playerId: "3", name: "Hampus", rank: 1300}]
};

//currently not used
function req(){
    const Http = new XMLHttpRequest();
    const url='https://jsonplaceholder.typicode.com/posts';
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = (e) => {
    console.log(Http.responseText)
    }

}
//this waits for document to load and then we set the dropdowns
document.addEventListener('DOMContentLoaded', function() {
    //THIS IS WHERE WE NEED TO QUERY DATABASE FOR GAMES
    changeDrop(resp);
}, false);

//FETCHES THE NAMES FOR PLAYES OF A CERTAIN GAME FROM DB
function fetchNames(game){
    //e is the person which has all the attributes
    //some jquery cause im lazy like dat, haters gon h8
    $("#names").empty();
    //THIS IS WHERE WE NEED TO QUERY DATABASE FOR NAMES FOR SAID GAME
    console.log(game);
    rankings.rankings.forEach(e => {
        dispNames(e);
    });
    document.getElementById("mySidenav").style.width = "0";
}


//diplays the names, person should contain all the info about said person in correct format 
function dispNames(person){
    
    var ul = document.querySelector("#names");
    
    var node = document.createElement("LI");
    node.setAttribute("class", "list-group-item");
    var textNode = document.createTextNode(person.name + "       " + person.rank);
    node.appendChild(textNode);
    ul.appendChild(node);

}

//CHANGES THE GAMES DISPLAYED BY SIMEMENU
function changeDrop(r){
    var ul = document.querySelector("#games");

    r.games.forEach(elem => {
        //this needs to be a loop making LI elements
        var node = document.createElement("LI");
        var aNode = document.createElement("A");
        aNode.setAttribute("href", "#"+elem.name);
        aNode.setAttribute("onclick","fetchNames(\"" + elem.name + "\")");
        var textNode = document.createTextNode(elem.name);
        aNode.appendChild(textNode);
        node.appendChild(aNode);
        ul.appendChild(node);
    });

    
}

//looks for the name and sends it
function addPlayer(){
    var form = document.querySelector("#nameInput").value;
    console.log(form);
}
 