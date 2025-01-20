let stompClient = null;
const messageContainer = document.getElementById("messages");
const usernameInput = document.getElementById("usernameInput");
const messageInput = document.getElementById("messageInput");
const sendButton = document.getElementById("sendButton");
const nameGroup = document.getElementById("nameGroupChat");
const urlParams = new URLSearchParams(window.location.search);
const roomName = urlParams.get("roomName");


nameGroup.textContent = `Room: ${roomName}`;

// Connect to WebSocket
function connect() {
    const socket = new SockJS("http://localhost:8080/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log("Connected to WebSocket");
        sendButton.disabled = false; // Enable send button after successful connection

        console.log("hohoho");
        
        // Subscribe to the public topic
        stompClient.subscribe(`/topic/${roomName}`, (payload) => {
            const message = JSON.parse(payload.body);
            console.log("Received message:", message);
            addMessageToChat(message.sender, message.content);
        });

        // Optionally fetch history if available
        fetchChatHistory();
    }, (error) => {
        console.error("WebSocket connection error:", error);
        alert("Failed to connect to WebSocket. Please refresh and try again.");
    });
}

// Send message
function sendMessage() {
    const sender = usernameInput.value.trim();
    const content = messageInput.value.trim();

    if (!sender || !content) {
        alert("Please enter your name and message!");
        return;
    }

    if (!stompClient || !stompClient.connected) {
        alert("WebSocket is not connected. Please wait...");
        return;
    }

    const chatMessage = { sender, content };
    stompClient.send(`/app/sendMessage/${roomName}`, {}, JSON.stringify(chatMessage));
    messageInput.value = ""; // Clear the input after sending
}

// Add message to chat UI
function addMessageToChat(sender, content) {
    console.log("hihihihi");
    
    const messageElement = document.createElement("div");
    messageElement.classList.add("message");
    messageElement.textContent = `${sender}: ${content}`;
    messageContainer.appendChild(messageElement);
    messageContainer.scrollTop = messageContainer.scrollHeight; // Auto-scroll
}

// Fetch chat history (optional)
function fetchChatHistory() {
    fetch("http://localhost:8080/api/chat/history") // Replace with your endpoint
        .then(response => response.json())
        .then(data => {
            data.forEach(message => addMessageToChat(message.sender, message.content));
        })
        .catch(error => console.error("Failed to fetch chat history:", error));
}

// Event listeners
sendButton.addEventListener("click", sendMessage);

// Auto-connect when the page loads
window.addEventListener("load", connect);
