var currentCard = document.getElementById("imageDiv").dataset.currentCard;
var copies = document.getElementById("copiesInput").dataset.copies;

async function updateCardDetails(saved) {
    var currentCopies;

    if (document.getElementById("copiesInput").checkVisibility) {
        currentCopies = document.getElementById("copiesInput").value;
        if (!saved) {
            currentCopies = -1
        } else if (saved && currentCopies < 1) {
            alert("Can't update a card with less than 0 copies")
            return;
        }
    } else {
        currentCopies = 1;
    }

    print("aqui: " + currentCard)
    fetch('http://localhost:8010/update-card?copies=' + currentCopies, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: currentCard
        })
        .then(() => {
            if (saved) {
                alert("Card copies updated successfully");
            } else {
                alert("Card saved on DB");
            }
        })
        .catch((error) => {
        console.error('There was an error updating the DB:', error);
        });
}

function setInputValue() {
    document.getElementById("copiesInput").value = copies;
}