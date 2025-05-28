// 1. JavaScript Basics & Setup
console.log("Welcome to the Community Portal");

window.addEventListener('load', () => {
  alert("Page fully loaded! Welcome to the Community Portal.");
});

// 5. Event class with prototype method
class Event {
  constructor(name, date, category, location, seats) {
    this.name = name;
    this.date = new Date(date); // Date object
    this.category = category;
    this.location = location;
    this.seats = seats; // available seats
    this.registered = 0; // track registered count
  }

  checkAvailability() {
    return this.seats > this.registered;
  }
}

// 6. Array to manage community events
const events = [
  new Event("Music Concert", "2025-06-20", "music", "City Park", 5),
  new Event("Coding Workshop", "2025-06-25", "workshop", "Library", 10),
  new Event("Yoga Session", "2025-06-22", "wellness", "Community Center", 8),
];

// Closure to track total registrations per category
function registrationTracker() {
  const counts = {};
  return function(category) {
    counts[category] = (counts[category] || 0) + 1;
    return counts[category];
  };
}
const trackRegistration = registrationTracker();

// DOM Elements
const eventsContainer = document.querySelector('#eventsContainer') || createEventsContainer();
const categoryFilter = document.querySelector('#categoryFilter');
const searchInput = document.querySelector('#searchInput');
const registrationForm = document.querySelector('#registrationForm');
const formMessage = document.querySelector('#formMessage');
const eventSelect = document.querySelector('select[name="event"]');

// Utility: create event card DOM element
function createEventCard(event) {
  const card = document.createElement('div');
  card.classList.add('eventCard');
  card.innerHTML = `
    <h3>${event.name}</h3>
    <p><strong>Date:</strong> ${event.date.toDateString()}</p>
    <p><strong>Category:</strong> ${event.category}</p>
    <p><strong>Location:</strong> ${event.location}</p>
    <p><strong>Seats Left:</strong> ${event.seats - event.registered}</p>
    <button ${!event.checkAvailability() ? "disabled" : ""} data-event="${event.name}">Register</button>
  `;
  return card;
}

// 7. Render events dynamically with filtering
function renderEvents(filterCategory = 'all', searchTerm = '') {
  eventsContainer.innerHTML = '';

  // 3. Conditionals to show only upcoming events with seats
  const now = new Date();
  let filteredEvents = events.filter(ev => {
    return ev.date >= now && ev.checkAvailability();
  });

  if (filterCategory !== 'all') {
    filteredEvents = filteredEvents.filter(ev => ev.category === filterCategory);
  }
  if (searchTerm) {
    filteredEvents = filteredEvents.filter(ev => ev.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }

  if(filteredEvents.length === 0) {
    eventsContainer.innerHTML = "<p>No upcoming events found matching criteria.</p>";
    eventSelect.innerHTML = '<option value="">-- No events available --</option>';
    return;
  }

  filteredEvents.forEach(ev => {
    eventsContainer.appendChild(createEventCard(ev));
  });

  // Update eventSelect options
  eventSelect.innerHTML = filteredEvents.map(ev => `<option value="${ev.name}">${ev.name}</option>`).join('');
}

// 4. Add event (reusable function)
function addEvent(name, date, category, location, seats) {
  events.push(new Event(name, date, category, location, seats));
}

// 8. Event delegation for register buttons
eventsContainer.addEventListener('click', e => {
  if (e.target.tagName === 'BUTTON' && e.target.dataset.event) {
    const eventName = e.target.dataset.event;
    const ev = events.find(ev => ev.name === eventName);

    if (!ev) return;

    // Register user prompt (simulate form with prompt)
    const name = prompt("Enter your name:");
    const email = prompt("Enter your email:");

    if (!name || !email) {
      alert("Registration failed: Name and Email are required.");
      return;
    }

    try {
      registerUser(ev, name, email);
      alert(`Registration successful for ${name} on event: ${ev.name}`);
      renderEvents(categoryFilter.value, searchInput.value);
    } catch (error) {
      alert(error.message);
    }
  }
});

// 9. Simulate async fetch of events (mock API)
async function fetchEventsMock() {
  // Show loading spinner
  formMessage.textContent = "Loading events...";
  await new Promise(res => setTimeout(res, 1000)); // simulate delay
  formMessage.textContent = "";
  renderEvents();
}

// 4. Register user function with error handling and seat count decrement
function registerUser(eventObj, name, email) {
  if (!eventObj.checkAvailability()) {
    throw new Error("Sorry, this event is fully booked.");
  }

  // 2. Use let for seat decrement
  eventObj.registered++;
  trackRegistration(eventObj.category);
}

// 11. Registration form submit
registrationForm.addEventListener('submit', e => {
  e.preventDefault();
  const name = registrationForm.name.value.trim();
  const email = registrationForm.email.value.trim();
  const eventName = registrationForm.event.value;

  if (!name || !email || !eventName) {
    formMessage.textContent = "Please fill out all fields correctly.";
    formMessage.style.color = "red";
    return;
  }

  const ev = events.find(ev => ev.name === eventName);

  try {
    registerUser(ev, name, email);
    formMessage.textContent = `Thank you ${name}, you have registered for ${eventName}!`;
    formMessage.style.color = "green";
    registrationForm.reset();
    renderEvents(categoryFilter.value, searchInput.value);
  } catch (error) {
    formMessage.textContent = error.message;
    formMessage.style.color = "red";
  }
});

// 8. Filters and search handlers
categoryFilter.addEventListener('change', () => {
  renderEvents(categoryFilter.value, searchInput.value);
});
searchInput.addEventListener('input', () => {
  renderEvents(categoryFilter.value, searchInput.value);
});

// Initialize UI
fetchEventsMock();
