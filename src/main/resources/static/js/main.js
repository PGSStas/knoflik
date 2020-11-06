'use strict';

let messageInput = document.querySelector('#code');

document.getElementById("button1").onclick = createRoom;
document.getElementById("button2").onclick = checkRoom;

async function createRoom(event) {
    let req = await fetch("/api/rooms/new");
    console.info(req)
    if (req.ok) {
        let id = await req.text();
        await enterRoom(id);
    }
}

async function checkRoom() {
    await enterRoom(messageInput.textContent);
}

async function enterRoom(roomId) {
    let req = await fetch("api/redirect/room?roomId=" + roomId);
    document.location.href = req.url;
}
