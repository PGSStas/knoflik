'use strict';

let messageInput = document.getElementById('codeInputField');

document.getElementById("createRoomButton").onclick = createRoom;
document.getElementById("submitCodeButton").onclick = checkRoom;

async function createRoom(event) {
    let req = await fetch("/api/rooms/new");
    console.info(req)
    if (req.ok) {
        let id = await req.text();
        enterRoom(id);
    }
}

function checkRoom() {
    enterRoom(messageInput.value);
}

function enterRoom(roomId) {
    document.location.href = "/room.html?id=" + roomId;
}
