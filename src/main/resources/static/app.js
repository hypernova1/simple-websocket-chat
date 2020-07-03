const roomId = location.href.split('/')[4];
let stompClient = null;

function setConnected(connected) {
    document.querySelector('#connect').disabled = connected;
    document.querySelector('#disconnect').disabled = !connected;
    if (connected) {
        document.querySelector('#conversation').style.display = 'block';
    } else {
        document.querySelector('#conversation').style.display = 'none';
    }
    document.querySelector('#greetings').innerHTML = '';
}

function connect() {
    const socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ', frame);
        stompClient.subscribe(`/subscribe/${roomId}`, (message) => {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log('Disconnect');
}

function sendMessage() {
    stompClient.send(`/app/${roomId}`, {}, JSON.stringify({
        nickname: document.querySelector('#nickname').value,
        content: document.querySelector('#content').value,
    }));
}

function showMessage(message) {
    const tr = document.createElement('tr');
    const td = document.createElement('td');
    td.innerText = message.nickname + ": " + message.content;
    tr.appendChild(td);
    document.querySelector('#greetings').appendChild(tr);
}

window.onload = function() {
    const forms = document.querySelectorAll('form');
    Array.from(forms).forEach((form) => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
        });
    });
    document.querySelector('#connect').addEventListener('click', () => {
        connect();
    });
    document.querySelector('#disconnect').addEventListener('click', () => {
        disconnect();
    });
    document.querySelector('#send').addEventListener('click', () => {
        sendMessage();
    });
};