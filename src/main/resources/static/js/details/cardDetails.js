var currentCard = document.getElementById("imageDiv").dataset.currentCard;
var copies = document.getElementById("copiesInput").dataset.copies;

async function updateCardDetails(copies, saved) {
    print("aqui: " + currentCard)
    fetch('http://localhost:8010/update-card?copies=' + copies, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: currentCard
        })
        .then(alert("Card updated successfully"))
        .catch((error) => {
        console.error('There was an error updating the DB:', error);
        });
}

function setInputValue() {
    document.getElementById("copiesInput").value = copies;
}