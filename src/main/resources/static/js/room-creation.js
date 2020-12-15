'use strict';

function leaderCheckBoxClick() {
    let leaderCheckBox = document.querySelector('#leader')
    let showQuestionsCheckBox = document.querySelector('#show-questions')
    let answerCheckBox = document.querySelector('#oral-answer')
    if (leaderCheckBox.checked) {
        showQuestionsCheckBox.disabled = false;
        answerCheckBox.disabled = false;
    } else {
        showQuestionsCheckBox.disabled = true;
        answerCheckBox.disabled = true;
        showQuestionsCheckBox.checked = true;
        answerCheckBox.checked = false;
    }
}