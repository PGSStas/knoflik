'use strict';

let leaderCheckBox = document.querySelector('#leader')
leaderCheckBox.onclick = leaderCheckBoxClick();
let showQuestionsCheckBox = document.querySelector('#show-questions')
let answerCheckBox = document.querySelector('#oral-answer')

function leaderCheckBoxClick() {
    if (leaderCheckBox.checked) {
        showQuestionsCheckBox.enabled = true;
        answerCheckBox.enabled = true;
    } else {
        showQuestionsCheckBox.enabled = false;
        answerCheckBox.enabled = false;
    }
}
