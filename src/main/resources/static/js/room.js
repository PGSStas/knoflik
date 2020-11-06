'use strict';

let header = document.querySelector('#header');

let roomId = null;

document.addEventListener("DOMContentLoaded", setRoomId);

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

function setRoomId() {
    let id = getQueryVariable("id");
    roomId = id;
    if (roomId != null) {
        header.textContent = "YOU ENTERED ROOM: " + id;
    }
}