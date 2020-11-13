'use strict';

let header = document.querySelector('#header');
let paragraph = document.querySelector('#p');

let roomId = null;
let stompClient = null;

document.addEventListener("DOMContentLoaded", setRoom);

function getQueryVariable(variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return null;
}

async function isRoomValid(id) {
    let req = await fetch("api/rooms/" + id)
    if (req.ok) {
        let text = await req.text();
        console.log(text)
        return text !== "";
    }
    return false;
}

async function setRoom() {
    let id = getQueryVariable("id");

    if (!await isRoomValid(id)) {
        location.replace("/404.html");
    }
    roomId = id;
    if (roomId != null) {
        header.textContent = "YOU ENTERED ROOM: " + roomId;
    }

    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

async function onConnected() {
    // Subscribe to the Public Topic
    await stompClient.subscribe('/topic/room/' + roomId, onMessageReceived);
    await fetch("api/rooms/" + roomId + "/addUser", {method: 'POST'});
}

function onMessageReceived(payload) {
    let users = JSON.parse(payload.body);
    let activeUsers = "ActiveUsers: ";
    users.forEach(element => activeUsers += element["username"] + " ");
    paragraph.textContent = activeUsers ;
}

function onError() {
    console.log("ERROR");
}
