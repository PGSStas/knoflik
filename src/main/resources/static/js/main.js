'use strict';

async function createRoom() {
    let req = await fetch("/api/rooms/new");
    console.info(req)
    if (req.ok) {
        let id = await req.text();
        document.location.href = "/room-creation";
    }
}

function enterRoom() {
    let codeInputField = document.getElementById("codeInputField");
    document.location.href = "/room?id=" + codeInputField.value;
}
