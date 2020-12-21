'use strict';

let header = document.querySelector('#header');
let paragraph = document.querySelector('#p');

let roomId = null;
let stompClient = null;

let score = 0;
let currentNominal = 0;
let currentQuestion = 0;
let isAdmin = false;

document.addEventListener("DOMContentLoaded", setRoom)
document.getElementById("startQuestionsButton").onclick = startQuiz;

function getQueryVariable(variable) {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has(variable)) {
        return urlParams.get(variable);
    }
    return null;
}

async function isRoomValid(id) {
    if (id === "") {
        return false;
    }
    let req = await fetch("api/rooms/" + id)
    if (req.ok) {
        let text = await req.text();

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

    let socket = new SockJS('/secured/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({room: roomId}, onConnected, onError);
}

async function onConnected() {
    await stompClient.subscribe(
        '/secured/topic/room/' + roomId + "/answer", onGetAnswerVerdict);
    await stompClient.subscribe(
        '/secured/topic/room/' + roomId + "/next", onNextQuestion);

    let req = await fetch("api/rooms/" + roomId + ".isAdmin");

    document.querySelector("#exit").style.display = "none";
    document.querySelector("#knoflik").style.display = "none";
    document.querySelector("#right").style.display = "none";
    document.querySelector("#false").style.display = "none";

    if (req.ok) {
        let text = await req.text();
        console.log("ADMIN " + text);
        if (text == "true") {
            await stompClient.subscribe(
                '/secured/topic/room/' + roomId + "/admin", onGetAdminInfo);
            isAdmin = true;
            document.querySelector("#score").style.display = "none";
        } else {
            document.querySelector("#nextButton").style.display = "none";
        }
    }

    await fetch("api/rooms/" + roomId + "/addUser", {method: 'POST'});
    stompClient.send("/secured/app/connect/" + roomId, {});
}

function onGetAnswerVerdict(payload) {
    // Сюда приходит или надпись вида "Stop, answering PLAYER",
    // где вместо PLAYER - имя текущего игрока
    // Или вердикт "true", если администратор сказал, что игрок
    // ответил правильно, или вердикт "false", если администратор сказал,
    // что игрок ответил неправильно
    if (isAdmin) {
        if (payload.body.startsWith("Stop")) {
            document.querySelector('#right').style.display = "";
            document.querySelector('#false').style.display = "";
            document.querySelector("#nextButton").style.display = "none";
        } else {
            document.querySelector('#right').style.display = "none";
            document.querySelector('#false').style.display = "none";
            if (payload.body.startsWith("true")) {
                document.querySelector("#nextButton").style.display = "";
            }
        }
    } else {
        if (payload.body.startsWith("Stop") || payload.body.startsWith("true")) {
            document.querySelector('#knoflik').style.display = "none";
            if (payload.body.startsWith("true")) {
                score += currentNominal;
            }
        } else {
            document.querySelector('#knoflik').style.display = "";
            score -= currentNominal;
        }
        document.querySelector('#score').textContent = "YOUR SCORE: " + score;
    }
    if (currentQuestion > topics * 5 && payload.body.startsWith("true")) {
        document.querySelector('#knoflik').style.display = "none";
        document.querySelector('#false').style.display = "none";
        document.querySelector('#right').style.display = "none";
        document.querySelector('#nextButton').style.display = "none";
        document.querySelector('#exit').style.display = "";
    }
    console.log(payload);
}

function onNextQuestion(payload) {
    // Сюда приходит текст вопроса
    currentNominal = parseInt(payload.body.substr(0, 2));
    // проверка нужна потому что 10 почему то не парсится, возможно наши файлы битые (выкидывает Nan)
    if (currentNominal < 10) {
        currentNominal *= 10;
    }
    currentQuestion += 1;
    if (isAdmin) {
        document.querySelector("#nextButton").style.display = "none";
    } else {
        document.querySelector("#knoflik").style.display = "";
    }
    document.querySelector('#question').textContent = payload.body;
    console.log(payload);
}

function onGetAdminInfo(payload) {
    // Сюда приходит ответ на вопрос
    if (isAdmin) {
        document.querySelector('#answer').textContent = payload.body;
    }
    console.log(payload);
}

async function sendAnswerApprove() {
    await fetch("api/rooms/" + roomId + "/answer.true", {method: 'POST'});
}

async function sendAnswerDisapprove() {
    await fetch("api/rooms/" + roomId + "/answer.false", {method: 'POST'});
}

async function sendVoiceAnswer() {
    await fetch("api/rooms/" + roomId + "/answer", {method: 'POST'});
}

async function getNextQuestion() {
    await fetch("api/rooms/" + roomId + "/nextQuestion", {method: 'POST'});
}

function onMessageReceived(payload) {
    let users = JSON.parse(payload.body);
    let activeUsers = "ActiveUsers: ";
    users.forEach(element => activeUsers += element["username"] + " ");
    paragraph.textContent = activeUsers;
}

function onError() {
    console.log("ERROR");
}

function startQuiz() {
    document.location.href = "/quiz.html?id=" + id;
}

function exit() {
    document.location.href = "/";
}
