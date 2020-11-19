'use strict';

let codeInputField = document.getElementById('codeInputField');

document.getElementById("createRoomButton").onclick = createRoom;
document.getElementById("submitCodeButton").onclick = enterRoom;

async function createRoom() {
    let req = await fetch("/api/rooms/new");
    console.info(req)
    if (req.ok) {
        let id = await req.text();
        document.location.href = "/room-creation";
    }
}

function enterRoom() {
    document.location.href = "/room.html?id=" + codeInputField.value;
}
