function openNav() {
  document.getElementById("nav").style.width = "20vw";
}

function closeNav() {
  document.getElementById("nav").style.width = "0";
}

document.getElementById("cardSearchInput").addEventListener('keyup', (event) => {
  if(event.key == 'Enter') {
    var searchInput = document.getElementById("cardSearchInput").value;
    searchInput = searchInput.replaceAll(" ", "+");
    console.log("Performing name search");
    performCardSearch(searchInput);
  }
});

async function performCardSearch(searchText) {
  await fetch('https://api.scryfall.com/cards/named?fuzzy=' + searchText + '&format=json', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
      },
    show: false
  }).then((response) => {
    if(response.status === 404) {
      console.log("Performing id search");
      performIdSearch(searchText);
    } else if (response.status === 200) {
      window.location.href = 'http://localhost:8010/details/name/' + searchText;
    }
  });
}

async function performIdSearch(searchText) {
  await fetch('https://api.scryfall.com/cards/' + searchText + '?format=json', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
      },
    show: false
  }).then((response) => {
    if(response.status === 404) {
      alert("Jaja lol  no hay de eso")
    } else if (response.status === 200){
      window.location.href = 'http://localhost:8010/details/id/' + searchText;
    }
  });
}