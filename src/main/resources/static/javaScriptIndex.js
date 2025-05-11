const restaurants = [
  { name: "Pizza Napoli", cuisine: "italiana", price: "2", rating: 4.5, lat: 40.4168, lng: -3.7038 },
  { name: "Sushi Tokyo", cuisine: "japonesa", price: "3", rating: 4.8, lat: 40.4185, lng: -3.7060 },
  { name: "Taco Loco", cuisine: "mexicana", price: "1", rating: 4.1, lat: 40.4153, lng: -3.7072 }
];

let map;
let markers = [];

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 40.4168, lng: -3.7038 },
    zoom: 14
  });

  displayRestaurants(restaurants);
}

function applyFilters() {
  const cuisine = document.getElementById("cuisineFilter").value;
  const price = document.getElementById("priceFilter").value;

  const filtered = restaurants.filter(r => {
    return (!cuisine || r.cuisine === cuisine) &&
           (!price || r.price === price);
  });

  displayRestaurants(filtered);
}

function displayRestaurants(list) {
  const container = document.getElementById("restaurantList");
  container.innerHTML = "";

  markers.forEach(m => m.setMap(null));
  markers = [];

  if (list.length === 0) {
    container.innerHTML = "<p>No hay resultados</p>";
    return;
  }

  list.forEach(r => {
    const div = document.createElement("div");
    div.className = "restaurant";
    div.innerHTML = `<strong>${r.name}</strong><br>
                     Cocina: ${r.cuisine}<br>
                     Precio: ${"€".repeat(r.price)}<br>
                     Valoración: ${r.rating}`;
    container.appendChild(div);

    const marker = new google.maps.Marker({
      position: { lat: r.lat, lng: r.lng },
      map: map,
      title: r.name
    });
    markers.push(marker);
  });
}
