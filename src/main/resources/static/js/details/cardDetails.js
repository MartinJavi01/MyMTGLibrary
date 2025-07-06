async function updateCardDetails(card, copies, saved) {
    print("aqui")
    await fetch("http://localhost:8010/update-card?card=" + card + "&copies=" + copies);
    if (saved) {
        alert("Card copies updated!")
    } else {
        alert("Card saved on BD")
    }
}
