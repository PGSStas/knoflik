'use strict';

async function createRoom() {
    let req = await fetch("/api/rooms/new");

    if (req.ok) {
        let id = await req.text();
        enterRoom(id);
    }
}

function connectRoom() {
    let codeInputField = document.getElementById("codeInputField");
    enterRoom(codeInputField.value);
}

function enterRoom(roomId) {
    let str = "/room?id=" + roomId
    console.log(str);
    document.location.href = str;
}
