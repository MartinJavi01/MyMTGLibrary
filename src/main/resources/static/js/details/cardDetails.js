var currentCard = document.getElementById("imageDiv").dataset.currentCard;

function init() {
    if (document.getElementById("copiesInput") !== null) {
        document.getElementById("copiesInput").value = document.getElementById("copiesInput").dataset.copies;
    }
}

function changeSide(currentFace, cardId) {
    currentFace = (currentFace == 0) ? 1 : 0;
    var redirectionId = '' + cardId;
    redirectionId = redirectionId.substring(1, redirectionId.length-1);
    window.location.replace('http://localhost:8010/details/id/' + redirectionId + "/" + currentFace)
}

async function updateCardDetails(saved) {
    var currentCopies;

    if (document.getElementById("copiesInput") !== null) {
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

    console.log(currentCard)

    await fetch('http://localhost:8010/update-card?copies=' + currentCopies, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: currentCard,
        show: false,
        })
        .then(() => {
            if (saved) {
                alert("Card copies updated successfully");
            } else if (!saved && currentCopies > 0){
                alert("Card saved on DB");
            } else {
                alert("Card deleted from DB");
            }
            window.location.reload();
        })
        .catch((error) => {
        console.error('There was an error updating the DB:', error);
        });
}