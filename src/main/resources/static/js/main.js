'use strict';

let messageInput = document.getElementById('code');

document.getElementById("button1").onclick = createRoom;
document.getElementById("button2").onclick = checkRoom;

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
