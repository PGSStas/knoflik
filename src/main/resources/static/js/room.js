'use strict';

let header = document.querySelector('#header');
let paragraph = document.querySelector('#p');

let roomId = null;
let stompClient = null;

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

    // todo: Следующие 3 строки помогут определить, админ ли пользователь
    // text истинные, если админ и ложный, если иначе.
    let req = await fetch("api/rooms/" + roomId + ".isAdmin");
    if (req.ok) {
        let text = await req.text();

        if (text === true) {
            await stompClient.subscribe(
                '/secured/topic/room/' + roomId + "/admin", onGetAdminInfo);
        }
    }

    await fetch("api/rooms/" + roomId + "/addUser", {method: 'POST'});
    stompClient.send("/secured/app/connect/" + roomId, {});
}

function onGetAnswerVerdict(payload) {
    // todo: Сюда приходит или надпись вида "Stop, answering PLAYER",
    // где вместо PLAYER - имя текущего игрока
    // Или вердикт "true", если администратор сказал, что игрок
    // ответил правильно, или вердикт "false", если администратор сказал,
    // что игрок ответил неправильно
    console.log(payload);
}

function onNextQuestion(payload) {
    // todo: Сюда приходит текст вопроса
    console.log(payload);
}

function onGetAdminInfo(payload) {
    // todo: Сюда приходит ответ на вопрос, его надо отображать ТОЛЬКО админу
    console.log(payload);
}

async function sendAnswerApprove() {
    // todo: Это действие надо навесить на кнопку, которая говорит, что ответ правильный.
    // После этого у администратора две кнопки (правильно и неправильно) должны скрыться
    // и на их месте должна появиться кнопка следующий вопрос
    await fetch("api/rooms/" + roomId + "/answer.true", {method: 'POST'});
}

async function sendAnswerDisapprove() {
    // todo: Это действие надо навесить на кнопку, которая говорит, что ответ неправильный.
    // После этого у администратора две кнопки (правильно и неправильно) должны скрыться
    // и на их месте должна появиться кнопка следующий вопрос
    await fetch("api/rooms/" + roomId + "/answer.false", {method: 'POST'});
}

async function sendVoiceAnswer() {
    // todo: Это действие надо навесить на кнопку knoflik.
    // После этого у игрока исчезает кнопка knoflik
    // А у администратора кнопки правильно и неправильно должны появиться,
    // а кнопка "следующий вопрос" должна исчезнуть
    await fetch("api/rooms/" + roomId + "/answer", {method: 'POST'});
}

async function getNextQuestion() {
    // todo: Это действие надо навесить на кнопку следующий вопрос
    // После этого у всех меняется поле вопроса, а у администратора еще и поле
    // ответа. Никаких перестановок кнопок делать не нужно.
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
